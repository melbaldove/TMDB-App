package io.melbybaldove.data.movie.entity

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
data class ReviewResults(
        val results: List<ReviewEntity>
)

data class ReviewEntity(
        val author: String,
        val content: String,
        val id: String,
        val url: String
)