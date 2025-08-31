package com.msk.common.util

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test


class ResourceTest {
    data class Dummy(val value: Int)

    @Test
    fun `Resource Success should hold data`() {
        val data = Dummy(5)
        val resource = Resource.Success(data)
        assertTrue(resource is Resource.Success)
        assertEquals(data, resource.data)
    }

    @Test
    fun `Resource Error should hold errorCategory and data`() {
        val data = Dummy(2)
        val resource = Resource.Error(ErrorCategory.UnknownError, data)
        assertTrue(resource is Resource.Error)
        assertEquals(ErrorCategory.UnknownError, resource.errorCategory)
        assertEquals(data, resource.data)
    }

    @Test
    fun `Resource Loading instance type check`() {
        val resource: Resource<Dummy> = Resource.Loading
        assertTrue(resource is Resource.Loading)
    }

    @Test
    fun `onSuccess should run action on Success`() {
        val data = Dummy(10)
        val resource: Resource<Dummy> = Resource.Success(data)
        var calledWith: Dummy? = null
        resource.onSuccess { calledWith = it }
        assertEquals(data, calledWith)
    }

    @Test
    fun `onError should run action on Error`() {
        val data = Dummy(42)
        val resource: Resource<Dummy> = Resource.Error(ErrorCategory.Timeout, data)
        var calledErrorCategory: ErrorCategory? = null
        var calledData: Dummy? = null
        resource.onError { ec, d ->
            calledErrorCategory = ec; calledData = d
        }
        assertEquals(ErrorCategory.Timeout, calledErrorCategory)
        assertEquals(data, calledData)
    }

    @Test
    fun `onLoading should run action on Loading`() {
        val resource: Resource<Nothing> = Resource.Loading
        var invoked = false
        resource.onLoading { invoked = true }
        assertTrue(invoked)
    }
}
