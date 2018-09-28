package io.melbybaldove.remote.movie.response

import io.melbybaldove.data.movie.entity.MovieEntity

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
data class FetchTrendingResponse(val results: List<MovieEntity>)