package io.melbybaldove.presentation.movie.detail.model

import io.melbybaldove.presentation.movie.model.MovieModel

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
data class MovieDetailModel(
        val movieModel: MovieModel,
        val backdropPath: String,
        val inWatchlist: Boolean,
        val myRating: String,
        val director: String,
        val starring: String
)