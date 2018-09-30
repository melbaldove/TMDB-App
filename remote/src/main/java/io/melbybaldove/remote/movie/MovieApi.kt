package io.melbybaldove.remote.movie

import io.melbybaldove.data.movie.entity.MovieEntity
import io.melbybaldove.remote.movie.request.RateMovieRequest
import io.melbybaldove.remote.movie.response.FetchTrendingResponse
import io.melbybaldove.remote.movie.response.SearchMoviesResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

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

    @GET("movie/{movie_id}?append_to_response=account_states,credits,reviews")
    fun getMovie(@Path("movie_id") id: String): Single<MovieEntity>

    @POST("movie/{movie_id}/rating")
    fun rateMovie(@Path("movie_id") id: String,
                  @Body rateMovieRequest: RateMovieRequest): Completable

    @DELETE("movie/{movie_id}/rating")
    fun deleteRating(@Path("movie_id") id: String): Completable
}