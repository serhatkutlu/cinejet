package com.msk.network.util


import com.msk.common.util.Resource
import com.msk.network.result.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkBoundResourceTest {
    @Test
    fun `emits Loading then Success when fetch succeeds`() = runTest {
        val localData = MutableStateFlow(0)

        val flow = networkBoundResource(
            query = { localData.take(1) }, // finite Flow
            fetch = { NetworkResult.Success(1) },
            saveFetchResult = { value -> localData.value = value }
        )

        val emissions = flow.toList()
        assertEquals(listOf(Resource.Loading, Resource.Success(1)), emissions)
    }

}
