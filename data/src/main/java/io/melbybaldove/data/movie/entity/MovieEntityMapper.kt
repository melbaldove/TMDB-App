package io.melbybaldove.data.movie.entity

import io.melbybaldove.commons.mapper.Mapper
import io.melbybaldove.domain.movie.entity.Cast
import io.melbybaldove.domain.movie.entity.Crew
import io.melbybaldove.domain.movie.entity.Movie
import io.melbybaldove.domain.movie.entity.Review
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
object MovieEntityMapper : Mapper<MovieEntity, Movie> {
    private val df = SimpleDateFormat("yyy-MM-DD", Locale.US)
    private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    override fun map(from: MovieEntity) = Movie(
            id = from.id.toString(),
            title = from.title ?: "No Title",
            description = from.overview ?: "No Overview",
            backdrop = URI(BASE_IMAGE_URL + from.backdrop_path),
            poster = URI(BASE_IMAGE_URL + from.poster_path),
            date = (if (!from.release_date.isNullOrEmpty()) from.release_date else null)?.let(df::parse),
            rating = from.vote_average?.toFloat() ?: 0f,
            totalRates = from.vote_count ?: 0,
            inWatchList = from.account_states?.watchlist,
            myRating = from.account_states?.rated?.value,
            reviews = from.reviews?.results?.map {
                Review(
                        id = it.id,
                        url = it.url,
                        author = it.author,
                        content = it.content
                )
            } ?: emptyList(),
            cast = from.credits?.cast?.map {
                Cast(
                        id = it.id.toString(),
                        character = it.character,
                        name = it.name
                )
            },
            crew = from.credits?.crew?.map {
                Crew(
                        id = it.id.toString(),
                        department = it.department,
                        name = it.name,
                        job = it.job
                )
            }
    )
}