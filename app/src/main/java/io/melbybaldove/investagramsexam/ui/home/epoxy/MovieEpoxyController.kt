package io.melbybaldove.investagramsexam.ui.home.epoxy

import com.airbnb.epoxy.EpoxyController
import io.melbybaldove.presentation.movie.model.MovieModel

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class MovieEpoxyController(private val listener: (MovieModel) -> Unit) : EpoxyController() {
    private var movies = emptyList<MovieModel>()

    fun setMovies(movies: List<MovieModel>) {
        this.movies = movies
        requestModelBuild()
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