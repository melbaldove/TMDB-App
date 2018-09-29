package io.melbybaldove.domain.movie.entity

import io.melbybaldove.commons.RequestOptions

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
data class SearchMoviesRequest(
        val query: String,
        val requestOptions: RequestOptions
)