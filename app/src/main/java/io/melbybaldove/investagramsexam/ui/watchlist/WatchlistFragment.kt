package io.melbybaldove.investagramsexam.ui.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import io.melbybaldove.commons.LoadingOptions
import io.melbybaldove.investagramsexam.R
import io.melbybaldove.investagramsexam.dagger.BaseMvRxDaggerFragment
import io.melbybaldove.investagramsexam.ui.home.epoxy.MovieEpoxyController
import io.melbybaldove.investagramsexam.ui.util.DialogHelper
import io.melbybaldove.presentation.movie.MovieViewModel
import io.melbybaldove.presentation.movie.model.MovieModel
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class WatchlistFragment : BaseMvRxDaggerFragment() {
    @Inject
    lateinit var dialogHelper: DialogHelper
    private val movieViewModel: MovieViewModel by fragmentViewModel()
    private val movieController = MovieEpoxyController(::showMovieDetails)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieViewModel.loadWatchList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_home_swipe.setOnRefreshListener {
            movieViewModel.loadWatchList()
        }
        activity?.title = "Watchlist"
    }

    override fun invalidate() {
        withState(movieViewModel) { state ->
            dialogHelper.shouldShowLoading(LoadingOptions(isLoading = state.loadingOptions.isLoading
                    && state.movies == null))
            if (state.loadingOptions.isLoading) return@withState
            fragment_home_swipe.isRefreshing = false

            state.error?.let {
                dialogHelper.showError(it)
                return@withState
            }

            state.movies ?: return@withState
            showWatchlist(state.movies!!)
        }
    }

    private fun showWatchlist(movies: List<MovieModel>) {
        movieController.setMovies(movies)
        fragment_home_recycler.setController(movieController)
    }

    private fun showMovieDetails(movie: MovieModel) {
        findNavController().navigate(WatchlistFragmentDirections.actionMovieDetail(movie.id))
    }
}