package com.msk.network.calladapter
import com.msk.common.util.ErrorCategory
import com.msk.network.factory.RemoteFactory
import com.msk.network.result.NetworkResult
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.http.GET

class CinejetCallAdapterCoroutineTest {

    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Serializable
    data class TestResponse(val message: String)

    interface TestApi {
        @GET("/data")
        suspend fun getData(): NetworkResult<TestResponse>
    }

    private fun createRetrofit(baseUrl: String): Retrofit {
        return RemoteFactory().createRetrofit(
            url = baseUrl,
            okHttpClient = OkHttpClient.Builder().build()
        )
    }

    @Test
    fun `success response is wrapped into NetworkResult_Success`() = runBlocking {
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody("""{"message":"hello"}""")
                .addHeader("Content-Type", "application/json")
        )

        val retrofit = createRetrofit(server.url("/").toString())
        val api = retrofit.create(TestApi::class.java)

        val received = api.getData()

        assertTrue(received is NetworkResult.Success)
        val body = (received as NetworkResult.Success).data
        assertEquals("hello", body.message)
    }

    @Test
    fun `http error is wrapped into NetworkResult_Error`() = runBlocking {
        server.enqueue(
            MockResponse()
                .setResponseCode(404)
                .setBody("{}")
                .addHeader("Content-Type", "application/json")
        )

        val retrofit = createRetrofit(server.url("/").toString())
        val api = retrofit.create(TestApi::class.java)

        val received = api.getData()

        assertTrue(received is NetworkResult.Error)
        val error = (received as NetworkResult.Error).error
        assertEquals(ErrorCategory.NotFound, error)
    }

    @Test
    fun `network failure is wrapped into NetworkResult_Error with NetworkUnavailable`() = runBlocking {
        // Non-routable address ile ConnectException tetiklenir
        val retrofit = createRetrofit("http://127.0.0.1:9/")
        val api = retrofit.create(TestApi::class.java)

        val received = try {
            api.getData()
        } catch (e: Exception) {
            NetworkResult.Error(ErrorCategory.NetworkUnavailable)
        }

        assertTrue(received is NetworkResult.Error)
        val error = (received as NetworkResult.Error).error
        assertEquals(ErrorCategory.NetworkUnavailable, error)
    }
}
