package io.melbybaldove.investagramsexam.remote

import io.melbybaldove.investagramsexam.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class ApiKeyInterceptor @Inject constructor() : Interceptor {
    var sessionId: String? = null
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = chain.request().url().newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()
        val interceptedRequest = request.newBuilder().url(url).build()
        return chain.proceed(interceptedRequest)
    }
}