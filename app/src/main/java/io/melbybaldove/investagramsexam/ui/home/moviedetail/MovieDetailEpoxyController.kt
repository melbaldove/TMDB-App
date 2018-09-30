package io.melbybaldove.investagramsexam.ui.home.moviedetail

import com.airbnb.epoxy.Typed2EpoxyController
import io.melbybaldove.presentation.movie.detail.model.MovieDetailModel
import io.melbybaldove.presentation.movie.detail.model.ReviewModel
import io.melbybaldove.presentation.movie.model.MovieModel

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class MovieDetailEpoxyController(private val listener: Listener) : Typed2EpoxyController<MovieDetailModel, List<ReviewModel>>() {
    override fun buildModels(data: MovieDetailModel, data2: List<ReviewModel>) {
        movieOverview {
            id("overview")
            movieDetailModel(data)
            listener(listener)
        }
        reviewsHeading {
            id("review heading")
            if (data2.isEmpty()) {
                "No Reviews"
            } else {
                "Reviews"
            }.let(::headingText)
        }
        data2.map {
            reviewItem {
                id(it.id)
                review(it)
            }
        }
    }

    interface Listener {
        fun rate(movie: MovieModel, rating: Float)
        fun deleteRating(movie: MovieModel)
        fun addToWatchlist(movie: MovieModel)
    }
}