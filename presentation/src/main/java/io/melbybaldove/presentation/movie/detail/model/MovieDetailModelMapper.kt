package io.melbybaldove.presentation.movie.detail.model

import io.melbybaldove.commons.mapper.Mapper
import io.melbybaldove.domain.movie.entity.Movie
import io.melbybaldove.presentation.movie.model.MovieModelMapper

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
object MovieDetailModelMapper : Mapper<Movie, MovieDetailModel> {
    override fun map(from: Movie) = MovieDetailModel(
            movieModel = MovieModelMapper.map(from),
            backdropPath = from.backdrop.toString(),
            inWatchlist = from.inWatchList ?: false,
            myRating = from.myRating?.toString() ?: "0",
            director = from.crew?.first { it.job == "Director" }?.name ?: "N/A",
            starring = from.cast?.take(2)?.joinToString(separator = ", ") { it.name } ?: "N/A"
    )
}