package io.melbybaldove.remote

import io.melbybaldove.remote.movie.MovieApi
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
interface ApiClient {
    val movie: MovieApi
}

class ApiClientImpl @Inject constructor(private val retrofit: Retrofit) : ApiClient {
    override val movie = createService(MovieApi::class.java)

    private fun <T> createService(api: Class<T>): T = retrofit.create(api)
}