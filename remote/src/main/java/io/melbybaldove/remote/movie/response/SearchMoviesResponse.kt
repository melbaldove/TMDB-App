package io.melbybaldove.remote.movie.response

import io.melbybaldove.data.movie.entity.MovieEntity

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */

data class SearchMoviesResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<MovieEntity>
)
