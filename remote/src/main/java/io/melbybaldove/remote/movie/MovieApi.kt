package io.melbybaldove.remote.movie

import io.melbybaldove.remote.movie.response.FetchTrendingResponse
import io.reactivex.Single
import retrofit2.http.GET

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
interface MovieApi {
    @GET("trending/movie/day")
    fun fetchTrending(): Single<FetchTrendingResponse>
}