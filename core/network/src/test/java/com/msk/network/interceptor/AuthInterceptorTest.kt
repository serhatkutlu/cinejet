package com.msk.network.interceptor

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AuthInterceptorTest {

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

    @Test
    fun `interceptor adds api_key query parameter`() {
        val apiKeyValue = "test_key_123"

        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(apiKeyValue))
            .build()

        server.enqueue(MockResponse().setResponseCode(200).setBody("{}"))

        val request = Request.Builder()
            .url(server.url("/path?existing=1"))
            .build()

        client.newCall(request).execute().use { response ->
            response.body?.close()
        }

        val recorded = server.takeRequest()
        val recordedRequestUrl = recorded.requestUrl!!

        assertEquals("1", recordedRequestUrl.queryParameter("existing"))
        assertEquals(apiKeyValue, recordedRequestUrl.queryParameter("api_key"))
    }
}
