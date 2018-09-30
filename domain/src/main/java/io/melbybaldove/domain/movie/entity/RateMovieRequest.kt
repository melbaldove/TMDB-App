package io.melbybaldove.domain.movie.entity

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
data class RateMovieRequest(
        val movieId: String,
        val rating: Float
)