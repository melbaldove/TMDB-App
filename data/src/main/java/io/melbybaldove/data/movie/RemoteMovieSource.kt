package io.melbybaldove.data.movie

import io.melbybaldove.commons.PaginatedResult
import io.melbybaldove.commons.RequestOptions
import io.melbybaldove.data.movie.entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
interface RemoteMovieSource {
    fun getTrending(): Single<List<MovieEntity>>
    fun searchMovies(query: String, requestOptions: RequestOptions): Single<PaginatedResult<MovieEntity>>
    fun getMovie(id: String): Single<MovieEntity>
    fun rateMovie(id: String, rating: Float): Completable
    fun deleteRating(id: String): Completable
}