package com.rishi.newsapp.data.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val requestBuilder = originalRequest.newBuilder().addHeader("X-Api-Key", apiKey)/*.addHeader("User-Agent","abc")*/
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}