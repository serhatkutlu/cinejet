    package com.msk.database

    import android.content.Context
import androidx.paging.PagingSource
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.msk.database.dao.detail.MovieDetailDao
import com.msk.database.dao.search.SearchDao
import com.msk.database.dao.seeall.MovieDao
import com.msk.database.dao.seeall.MovieRemoteKeyDao
import com.msk.database.datasource.LocalMovieDataSource
import com.msk.database.model.detail.CastEntity
import com.msk.database.model.detail.GenreEntity
import com.msk.database.model.detail.ImagesEntity
import com.msk.database.model.detail.ImagesItemEntity
import com.msk.database.model.detail.MovieDetailEntity
import com.msk.database.model.detail.RecommendationEntity
import com.msk.database.model.movie.MovieEntity
import com.msk.database.model.movie.MovieRemoteKeyEntity
import com.msk.database.util.RoomTransactionProvider
import com.msk.model.common.MediaType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

    @RunWith(AndroidJUnit4::class)
    class CinejetDatabaseDaoTest {

        private lateinit var context: Context
        private lateinit var database: CinejetDatabase

        private lateinit var movieDao: MovieDao
        private lateinit var movieRemoteKeyDao: MovieRemoteKeyDao
        private lateinit var movieDetailDao: MovieDetailDao
        private lateinit var searchDao: SearchDao

        @Before
        fun setUp() {
            context = ApplicationProvider.getApplicationContext()
            database = Room.inMemoryDatabaseBuilder(context, CinejetDatabase::class.java)
                .allowMainThreadQueries()
                .build()
            movieDao = database.movieDao
            movieRemoteKeyDao = database.movieRemoteKeyDao
            movieDetailDao = database.movieDetailDao
            searchDao = database.movieSearchDao
        }

        @After
        fun tearDown() {
            database.close()
        }

        @Test
        fun insertAndQueryMoviesByMediaType_shouldReturnLimitedResults() = runBlocking {
            val movies = listOf(
                testMovie(networkId = 101, mediaType = MediaType.Popular, title = "A1"),
                testMovie(networkId = 102, mediaType = MediaType.Popular, title = "A2"),
                testMovie(networkId = 201, mediaType = MediaType.NowPlaying, title = "B1")
            )
            movieDao.insertMovies(movies)

            val popular = movieDao.getMoviesByMediaTypeLimited(MediaType.Popular, limit = 1).first()
            assertEquals(1, popular.size)
            assertEquals(MediaType.Popular, popular.first().mediaType)
        }

        @Test
        fun deleteMoviesByMediaType_shouldRemoveOnlyTargetType() = runBlocking {
            val movies = listOf(
                testMovie(networkId = 1, mediaType = MediaType.Trending, title = "T1"),
                testMovie(networkId = 2, mediaType = MediaType.Trending, title = "T2"),
                testMovie(networkId = 3, mediaType = MediaType.Discover, title = "D1")
            )
            movieDao.insertMovies(movies)

            movieDao.deleteMoviesByMediaType(MediaType.Discover)

            val trending = movieDao.getMoviesByMediaTypeLimited(MediaType.Trending, 10).first()
            val discover = movieDao.getMoviesByMediaTypeLimited(MediaType.Discover, 10).first()
            assertEquals(2, trending.size)
            assertEquals(0, discover.size)
        }

        @Test
        fun searchMoviesInMovieDao_shouldReturnMatchingTitles() = runBlocking {
            movieDao.insertMovies(
                listOf(
                    testMovie(networkId = 11, mediaType = MediaType.Popular, title = "Star Wars"),
                    testMovie(networkId = 12, mediaType = MediaType.Popular, title = "Stargate"),
                    testMovie(networkId = 13, mediaType = MediaType.Popular, title = "Other")
                )
            )

            val pagingSource = movieDao.searchMovies("Star")
            val result = pagingSource.load(
                PagingSource.LoadParams.Refresh(key = null, loadSize = 20, placeholdersEnabled = false)
            )

            require(result is PagingSource.LoadResult.Page)
            val titles = result.data.map { it.title }
            assertEquals(listOf("Star Wars", "Stargate"), titles.sorted())
        }

        @Test
        fun remoteKeyDao_crudOperationsWork() = runBlocking {
            val keys = listOf(
                MovieRemoteKeyEntity(
                    id = 100,
                    mediaType = MediaType.Popular,
                    prevPage = null,
                    nextPage = 2
                ),
                MovieRemoteKeyEntity(
                    id = 200,
                    mediaType = MediaType.NowPlaying,
                    prevPage = 1,
                    nextPage = 3
                )
            )
            movieRemoteKeyDao.insertAll(keys)

            val key = movieRemoteKeyDao.getByIdAndMediaType(100, MediaType.Popular)
            assertNotNull(key)
            assertEquals(2, key!!.nextPage)

            movieRemoteKeyDao.deleteByMediaType(MediaType.Popular)
            val deleted = movieRemoteKeyDao.getByIdAndMediaType(100, MediaType.Popular)
            assertNull(deleted)
        }

        @Test
        fun movieDetailDao_insertUpdateDelete() = runBlocking {
            val detail = testMovieDetail(id = 1L, title = "Inception", isFavourite = false)
            movieDetailDao.insert(detail)

            val fetched = movieDetailDao.getById(1).first()
            assertNotNull(fetched)
            assertEquals("Inception", fetched!!.title)
            assertEquals(false, fetched.isFavourite)

            movieDetailDao.updateFavoriteById(id = 1, isFavorite = true)
            val updated = movieDetailDao.getById(1).first()
            assertEquals(true, updated!!.isFavourite)

            movieDetailDao.deleteById(id = 1)
            val any = movieDetailDao.getAny().first()
            assertNull(any)
        }


        @Test
        fun searchDao_searchesInDetailTable() = runBlocking {
            movieDetailDao.insert(
                testMovieDetail(
                    id = 21,
                    title = "Avengers: Endgame",
                    isFavourite = false
                )
            )
            movieDetailDao.insert(
                testMovieDetail(
                    id = 22,
                    title = "Avengers: Infinity War",
                    isFavourite = false
                )
            )
            movieDetailDao.insert(testMovieDetail(id = 23, title = "Random"))

            val pagingSource = searchDao.searchMovies("Avengers")
            val result = pagingSource.load(
                PagingSource.LoadParams.Refresh(key = null, loadSize = 10, placeholdersEnabled = false)
            )

            require(result is PagingSource.LoadResult.Page)
            val titles = result.data.map { it.title }.sorted()
            assertEquals(listOf("Avengers: Endgame", "Avengers: Infinity War"), titles)
        }

        @Test
        fun localMovieDataSource_insertMoviesAndPagingRefresh() = runBlocking {
            val dataSource =
                LocalMovieDataSource(movieDao, movieRemoteKeyDao, RoomTransactionProvider(database))

            // Seed existing movies of Popular type
            movieDao.insertMovies(
                listOf(
                    testMovie(networkId = 1, mediaType = MediaType.Popular, title = "Old1"),
                    testMovie(networkId = 2, mediaType = MediaType.Popular, title = "Old2")
                )
            )

            // New movies and remote keys
            val newMovies = listOf(
                testMovie(networkId = 3, mediaType = MediaType.Popular, title = "New1"),
                testMovie(networkId = 4, mediaType = MediaType.Popular, title = "New2")
            )
            val keys = newMovies.map {
                MovieRemoteKeyEntity(
                    id = it.networkId,
                    mediaType = it.mediaType,
                    prevPage = 1,
                    nextPage = 3
                )
            }

            dataSource.insertMoviesWithPaging(
                mediaType = MediaType.Popular,
                movies = newMovies,
                remoteKeys = keys,
                isRefreshData = true
            )

            val popularAfter = dataSource.getByMediaType(MediaType.Popular, pageSize = 10).first()
            val titles = popularAfter.map { it.title }.sorted()
            assertEquals(listOf("New1", "New2"), titles)

            // Remote key exists
            val key = dataSource.getRemoteKeyByIdAndMediaType(3, MediaType.Popular)
            assertNotNull(key)
            assertEquals(3, key!!.nextPage)
        }

        private fun testMovie(
            networkId: Int,
            mediaType: MediaType,
            title: String,
        ) = MovieEntity(
            networkId = networkId,
            mediaType = mediaType,
            title = title,
            overview = "overview",
            voteAverage = 8.5,
            posterPath = null,
            backdropPath = null,
            releaseDate = null,
            lastFetchedTime = System.currentTimeMillis()
        )

        private fun testMovieDetail(
            id: Long,
            title: String,
            isFavourite: Boolean = false
        ): MovieDetailEntity {
            return MovieDetailEntity(
                id = id,
                adult = false,
                backdropPath = null,
                budget = 100000,
                isFavourite = isFavourite,
                images = ImagesEntity(
                    backdrops = listOf(ImagesItemEntity(1.7f, 1000, null, "/path.jpg", 8.0, 100, 1920)),
                    posters = listOf(ImagesItemEntity(0.7f, 800, null, "/poster.jpg", 7.5, 50, 1080))
                ),
                homepage = null,
                imdbId = null,
                originalLanguage = "en",
                originalTitle = title,
                genres = listOf(GenreEntity(1, "Action")),
                cast = listOf(CastEntity(1, "Actor", "Hero", null, "Actor")),
                overview = "overview",
                popularity = 123.4,
                posterPath = null,
                releaseDate = "2020-01-01",
                revenue = 1_000_000,
                runtime = 120,
                status = "Released",
                tagline = null,
                title = title,
                video = false,
                voteAverage = 8.7,
                voteCount = 1000,
                recommendations = listOf(RecommendationEntity(1, 10, 7.5f, "Rec1", null))
            )
        }
    }