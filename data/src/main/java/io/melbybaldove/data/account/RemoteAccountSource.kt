package io.melbybaldove.data.account

import io.melbybaldove.data.movie.entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
interface RemoteAccountSource {
    fun getWatchlist(): Single<List<MovieEntity>>
    fun addToWatchlist(movieId: String): Completable
    fun removeFromWatchlist(movieId: String): Completable
}