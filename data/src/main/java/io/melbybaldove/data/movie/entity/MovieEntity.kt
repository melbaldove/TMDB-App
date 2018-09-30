package io.melbybaldove.data.movie.entity

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
data class MovieEntity(
        val adult: Boolean?,
        val backdrop_path: String?,
        val genre_ids: List<Int>?,
        val id: Int?,
        val original_language: String?,
        val original_title: String?,
        val overview: String?,
        val poster_path: String?,
        val release_date: String?,
        val title: String?,
        val video: Boolean?,
        val vote_average: Double?,
        val vote_count: Int?,
        val popularity: Double?,
        val account_states: AccountStates?,
        val credits: CreditsEntity?,
        val reviews: ReviewResults?
)

data class AccountStates(
        val favorite: Boolean,
        val rated: Rated,
        val watchlist: Boolean
)

data class Rated(
        val value: Float
)

