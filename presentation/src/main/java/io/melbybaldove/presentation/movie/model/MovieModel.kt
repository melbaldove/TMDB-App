package io.melbybaldove.presentation.movie.model

import java.net.URI

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
data class MovieModel(
        val id: String,
        val poster: URI,
        val title: String,
        val date: String,
        val desc: String
)