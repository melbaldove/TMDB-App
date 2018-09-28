package io.melbybaldove.investagramsexam.ui.home.epoxy

import com.airbnb.epoxy.EpoxyController
import io.melbybaldove.presentation.movie.model.MovieModel

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class MovieEpoxyController : EpoxyController() {
    private var movies = emptyList<MovieModel>()
    private lateinit var listener: (MovieModel) -> Unit

    fun setMovies(movies: List<MovieModel>) {
        this.movies = movies
        requestModelBuild()
    }

    fun setListener(listener: (MovieModel) -> Unit) {
        this.listener = listener
    }

    override fun buildModels() {
        movies.map {
            movie {
                id(it.id)
                movie(it)
                listener(listener)
            }
        }
    }
}