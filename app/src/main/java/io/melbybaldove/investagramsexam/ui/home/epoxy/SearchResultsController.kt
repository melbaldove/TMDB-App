package io.melbybaldove.investagramsexam.ui.home.epoxy

import com.airbnb.epoxy.EpoxyController
import io.melbybaldove.presentation.movie.model.MovieModel

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class SearchResultsController(private val scrollListener: InfiniteScrollListener) : EpoxyController() {
    private var isLoading = false
    private var movies = arrayListOf<MovieModel>()
    private var query = ""

    fun appendMovies(movies: List<MovieModel>, query: String) {
        this.movies.addAll(movies)
        this.isLoading = false
        this.query = query
        requestModelBuild()
    }

    fun reset() {
        movies.clear()
        query = ""
        requestModelBuild()
    }

    override fun buildModels() {
        searchHeading {
            id("header")
            query(query)
        }
        movies.map {
            movie {
                id(it.id)
                movie(it)
            }
        }
        if (scrollListener.hasMoreToLoad())
            loaderModel {
                id("loader")
                onBind { _, _, _ ->
                    if (scrollListener.hasMoreToLoad())
                        scrollListener.loadMore()
                }
            }
    }

    interface InfiniteScrollListener {
        fun hasMoreToLoad(): Boolean
        fun loadMore()
    }
}