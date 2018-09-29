package io.melbybaldove.investagramsexam.ui.home

import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.*
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.EpoxyController
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import io.melbybaldove.authentication.Authenticator
import io.melbybaldove.commons.LoadingOptions
import io.melbybaldove.investagramsexam.R
import io.melbybaldove.investagramsexam.dagger.BaseMvRxDaggerFragment
import io.melbybaldove.investagramsexam.ui.home.epoxy.MovieEpoxyController
import io.melbybaldove.investagramsexam.ui.home.epoxy.SearchResultsController
import io.melbybaldove.investagramsexam.ui.util.DialogHelper
import io.melbybaldove.presentation.movie.MovieViewModel
import io.melbybaldove.presentation.movie.ScreenState.SEARCH
import io.melbybaldove.presentation.movie.ScreenState.TRENDING
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
    private var currentController: EpoxyController? = null
    private var searchViewMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!authenticator.isAuthenticated()) {
            findNavController().navigate(R.id.action_login)
        }
        setHasOptionsMenu(true)
        movieController.setListener(::showMovieDetails)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Trending Movies"
        fragment_home_swipe.setOnRefreshListener {
            searchViewMenuItem?.collapseActionView()
            loadTrending()
        }
        loadTrending()
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
            when (state.screenState) {
                TRENDING -> showTrendingMovies(state.movies!!)
                SEARCH -> showSearchResults(state.movies!!, state.query)
            }
        }
    }

    private fun loadTrending() {
        if(currentController != movieController) {
            currentController = movieController
            fragment_home_recycler.setController(movieController)
        }
        movieViewModel.loadTrending()
    }

    private val scrollListener = object : SearchResultsController.InfiniteScrollListener {
        override fun loadMore() = movieViewModel.loadMoreSearchResults()

        override fun hasMoreToLoad() = movieViewModel.hasMoreToLoad()
    }

    private val searchResultsController = SearchResultsController(scrollListener)

    private fun showTrendingMovies(movies: List<MovieModel>) {
        movieController.setMovies(movies)
    }

    private fun showSearchResults(movies: List<MovieModel>, query: String) {
        searchResultsController.appendMovies(movies, query)
        if (currentController != searchResultsController) {
            currentController = searchResultsController
            fragment_home_recycler.setController(searchResultsController)
        }
    }

    private fun showMovieDetails(movie: MovieModel) {

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.home_menu, menu)
        searchViewMenuItem = menu?.findItem(R.id.action_search)?.apply {
            setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(p0: MenuItem?) = true

                override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                    loadTrending()
                    return true
                }

            })
        }
        val searchView = searchViewMenuItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                searchResultsController.reset()
                movieViewModel.searchMovies(p0!!)
                return true
            }
            override fun onQueryTextChange(p0: String?) = false
        })
    }
}