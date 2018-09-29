package io.melbybaldove.remote.movie

import io.melbybaldove.remote.movie.response.FetchTrendingResponse
import io.melbybaldove.remote.movie.response.SearchMoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
interface MovieApi {
    @GET("trending/movie/day")
    fun fetchTrending(): Single<FetchTrendingResponse>

    @GET("search/movie")
    fun searchMovies(@Query("query") query: String,
                     @Query("page") page: Int): Single<SearchMoviesResponse>
}