package io.melbybaldove.presentation.movie.model

import io.melbybaldove.commons.mapper.Mapper
import io.melbybaldove.domain.movie.entity.Movie
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
object MovieModelMapper : Mapper<Movie, MovieModel> {
    private val df = SimpleDateFormat("MMM dd, yyy", Locale.US)
    override fun map(from: Movie) = MovieModel(id = from.id,
            title = from.title,
            poster = from.poster,
            date = from.date?.let(df::format) ?: "N/A",
            desc = from.description,
            rating = from.rating.toString(),
            totalRates = from.totalRates.toString())
}