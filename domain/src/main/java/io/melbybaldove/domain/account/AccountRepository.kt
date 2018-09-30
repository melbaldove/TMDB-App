package io.melbybaldove.domain.account

import io.melbybaldove.domain.movie.entity.Movie
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
interface AccountRepository {
    fun getWatchlist(): Single<List<Movie>>
    fun addToWatchlist(movieId: String): Completable
    fun removeFromWatchlist(movieId: String): Completable
}