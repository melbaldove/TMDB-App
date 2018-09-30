package io.melbybaldove.authentication

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
@Singleton
class SessionIdInterceptor @Inject constructor() : Interceptor {
    var sessionId: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        sessionId ?: return chain.proceed(chain.request())
        val request = chain.request()
        val url = chain.request().url().newBuilder()
                .addQueryParameter("session_id", sessionId!!)
                .build()
        val interceptedRequest = request.newBuilder().url(url).build()
        return chain.proceed(interceptedRequest)
    }
}