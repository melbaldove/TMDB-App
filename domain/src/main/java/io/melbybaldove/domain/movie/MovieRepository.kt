package io.melbybaldove.domain.movie

import io.melbybaldove.commons.PaginatedResult
import io.melbybaldove.commons.RequestOptions
import io.melbybaldove.domain.movie.entity.Movie
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
interface MovieRepository {
    fun getTrending(): Single<List<Movie>>
    fun searchMovies(query: String, requestOptions: RequestOptions): Single<PaginatedResult<Movie>>
    fun getMovie(id: String): Single<Movie>
    fun rateMovie(id: String, rating: Float): Completable
    fun deleteRating(id: String): Completable
}