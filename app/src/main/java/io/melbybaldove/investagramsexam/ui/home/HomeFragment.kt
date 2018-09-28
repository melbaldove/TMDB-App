package io.melbybaldove.investagramsexam.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import io.melbybaldove.authentication.Authenticator
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
class HomeFragment : BaseMvRxDaggerFragment() {
    @Inject
    lateinit var authenticator: Authenticator
    @Inject
    lateinit var dialogHelper: DialogHelper
    private val movieViewModel: MovieViewModel by fragmentViewModel()
    private val movieController = MovieEpoxyController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!authenticator.isAuthenticated()) {
            findNavController().navigate(R.id.action_login)
        }
        movieViewModel.loadTrending()
        movieController.setListener(::showMovieDetails)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_home_recycler.setController(movieController)
        activity?.title = "Trending Movies"
    }

    override fun invalidate() {
        withState(movieViewModel) { state ->
            dialogHelper.shouldShowLoading(state.loadingOptions)
            if (state.loadingOptions.isLoading) return@withState

            state.error?.let {
                dialogHelper.showError(it)
                return@withState
            }

            state.movies ?: return@withState
            showTrendingMovies(state.movies!!)
        }
    }

    private fun showTrendingMovies(movies: List<MovieModel>) {
        movieController.setMovies(movies)
    }


    private fun showMovieDetails(movie: MovieModel) {

    }
}