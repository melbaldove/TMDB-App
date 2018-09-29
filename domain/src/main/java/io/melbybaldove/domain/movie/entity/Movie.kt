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
        val date: Date?,
        val description: String
) {
    companion object {
        fun createTestMovie() = Movie(
                id = "someId",
                title = "someTitle",
                poster = URI("someUrl"),
                date = Date(),
                description = "someDescription"
        )
    }
}