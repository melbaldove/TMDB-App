package io.melbybaldove.remote.account.request

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
data class WatchlistRequest(
        val media_type: String = "movie",
        val media_id: String,
        val watchlist: Boolean
)