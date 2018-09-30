package io.melbybaldove.remote.account

import io.melbybaldove.data.account.RemoteAccountSource
import io.melbybaldove.remote.account.request.WatchlistRequest
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class RemoteAccountSourceImpl @Inject constructor(private val accountApi: AccountApi) : RemoteAccountSource {
    // Todo: Cache account id (silly API should just be receiving JWT)
    override fun getWatchlist() = accountApi.fetchAccountDetails().map { it.id }
            .flatMap(accountApi::fetchWatchlist)
            .map { it.results }!!

    override fun addToWatchlist(movieId: String) = accountApi.fetchAccountDetails().map { it.id }
            .flatMapCompletable {
                accountApi.editWatchlistFor(it, WatchlistRequest(media_id = movieId, watchlist = true))
            }!!

    override fun removeFromWatchlist(movieId: String) = accountApi.fetchAccountDetails().map { it.id }
            .flatMapCompletable {
                accountApi.editWatchlistFor(it, WatchlistRequest(media_id = movieId, watchlist = false))
            }!!
}