package io.melbybaldove.remote.account

import io.melbybaldove.remote.account.request.WatchlistRequest
import io.melbybaldove.remote.account.response.AccountResponse
import io.melbybaldove.remote.movie.response.MoviesResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
interface AccountApi {
    @GET("account/{account_id}/watchlist/movies")
    fun fetchWatchlist(@Path("account_id") id: String): Single<MoviesResponse>

    @GET("account")
    fun fetchAccountDetails(): Single<AccountResponse>

    @POST("account/{account_id}/watchlist")
    fun editWatchlistFor(@Path("account_id") id: String, @Body request: WatchlistRequest): Completable
}