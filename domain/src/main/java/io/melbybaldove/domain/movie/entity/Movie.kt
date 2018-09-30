package io.melbybaldove.domain.movie.entity

import java.net.URI
import java.util.*

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
data class Movie(
        val id: String,
        val title: String,
        val poster: URI,
        val backdrop: URI,
        val date: Date?,
        val rating: Float,
        val totalRates: Int,
        val description: String,
        val cast: List<Cast>?,
        val crew: List<Crew>?,
        val inWatchList: Boolean?,
        val myRating: Float?,
        val reviews: List<Review>
) {
    companion object {
        fun createTestMovie() = Movie(
                id = "someId",
                title = "someTitle",
                poster = URI("someUrl"),
                backdrop = URI("someUrl"),
                date = Date(),
                rating = 10f,
                totalRates = 500,
                description = "someDescription",
                cast = null,
                crew = null,
                inWatchList = null,
                myRating = null,
                reviews = emptyList()
        )
    }
}