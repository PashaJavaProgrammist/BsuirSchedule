package com.haretskiy.pavel.bsuirschedule.rest

import android.util.Log
import com.haretskiy.pavel.bsuirschedule.*
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import javax.inject.Inject

class JsonLoggingInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response? {
        val request = chain.request()
        try {
            val t1 = System.nanoTime()

            Log.d(INTERCEPTOR_TAG, "Sending request ${request.url()} Headers: ${request.headers()}")

            val response = chain.proceed(request)

            val responseBodyString = response.body()?.string() ?: EMPTY_STRING

            val t2 = System.nanoTime()

            val prettyString = responseBodyString.toPrettyFormat()
            val length = prettyString.length
            if (length > 4000) {
                val chunkCount = length / 4000
                for (i in 0..chunkCount) {
                    val max = 4000 * (i + 1)
                    if (max >= length) {
                        Log.d(INTERCEPTOR_TAG, "chunk $i of $chunkCount:")
                        Log.e(INTERCEPTOR_TAG, prettyString.substring(4000 * i))
                    } else {
                        Log.d(INTERCEPTOR_TAG, "chunk $i of $chunkCount:")
                        Log.e(INTERCEPTOR_TAG, prettyString.substring(4000 * i, max))
                    }
                }
            } else {
                Log.e(INTERCEPTOR_TAG, prettyString)
            }

            Log.d(INTERCEPTOR_TAG, "Received response for ${response.request().url()} for ${(t2 - t1) / 1e6} milliseconds ")

            val responseBody = response.body()

            return response.newBuilder().body(ResponseBody.create(responseBody?.contentType(), responseBodyString.toByteArray())).build()
        } catch (ex: Exception) {
            return chain.proceed(request)
        }
    }
}