package io.melbybaldove.remote

import io.melbybaldove.remote.account.AccountApi
import io.melbybaldove.remote.movie.MovieApi
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
interface ApiClient {
    val movie: MovieApi
    val account: AccountApi
}

class ApiClientImpl @Inject constructor(private val retrofit: Retrofit) : ApiClient {
    override val movie = createService(MovieApi::class.java)
    override val account = createService(AccountApi::class.java)

    private fun <T> createService(api: Class<T>): T = retrofit.create(api)
}