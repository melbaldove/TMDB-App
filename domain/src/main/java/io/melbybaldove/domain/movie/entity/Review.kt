package io.melbybaldove.domain.movie.entity

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
data class Review(
        val author: String,
        val content: String,
        val id: String,
        val url: String
)