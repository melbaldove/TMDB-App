package io.melbybaldove.data.account

import io.melbybaldove.data.movie.entity.MovieEntityMapper
import io.melbybaldove.domain.account.AccountRepository
import io.melbybaldove.domain.movie.entity.Movie
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class AccountDataRepository @Inject constructor(private val remoteAccountSource: RemoteAccountSource) : AccountRepository {
    override fun getWatchlist(): Single<List<Movie>> = remoteAccountSource.getWatchlist().map { it.map(MovieEntityMapper::map) }

    override fun addToWatchlist(movieId: String): Completable = remoteAccountSource.addToWatchlist(movieId)

    override fun removeFromWatchlist(movieId: String): Completable = remoteAccountSource.removeFromWatchlist(movieId)
}