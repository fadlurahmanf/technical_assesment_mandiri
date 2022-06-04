package com.technical_assessment.starterappmvvm.data.interceptor

import com.technical_assessment.starterappmvvm.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class QueryInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
        return chain.proceed(request.newBuilder().url(url).build())
    }

}