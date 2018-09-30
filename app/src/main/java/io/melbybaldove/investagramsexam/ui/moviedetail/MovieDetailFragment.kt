package io.melbybaldove.investagramsexam.ui.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import io.melbybaldove.commons.LoadingOptions
import io.melbybaldove.investagramsexam.R
import io.melbybaldove.investagramsexam.dagger.BaseMvRxDaggerFragment
import io.melbybaldove.investagramsexam.ui.util.DialogHelper
import io.melbybaldove.presentation.movie.detail.MovieDetailViewModel
import io.melbybaldove.presentation.movie.detail.model.MovieDetailModel
import io.melbybaldove.presentation.movie.detail.model.ReviewModel
import io.melbybaldove.presentation.movie.model.MovieModel
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class MovieDetailFragment : BaseMvRxDaggerFragment() {
    private val viewModel: MovieDetailViewModel by fragmentViewModel()
    private val controller = MovieDetailEpoxyController(object : MovieDetailEpoxyController.Listener {
        override fun rate(movie: MovieModel, rating: Float) = viewModel.rate(movie, rating)
        override fun deleteRating(movie: MovieModel) = viewModel.deleteRating(movie)
        override fun addToWatchlist(movie: MovieModel) = viewModel.addToWatchlist(movie)

        override fun removeFromWatchlist(movie: MovieModel) = viewModel.removeFromWatchlist(movie)
    })
    @Inject
    lateinit var dialogHelper: DialogHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieId = MovieDetailFragmentArgs.fromBundle(arguments).movieId
        viewModel.showMovie(movieId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_movie_detail_recycler.setController(controller)
        activity?.title = "Details"
    }

    override fun invalidate() {
        withState(viewModel) {
            dialogHelper.shouldShowLoading(LoadingOptions(isLoading = it.loadingOptions.isLoading))
            if (it.loadingOptions.isLoading) return@withState

            it.errorModel?.let {error ->
                dialogHelper.showError(error)
                return@withState
            }

            it.movie ?: return@withState
            showMovieDetail(it.movie!!, it.reviews!!)
        }
    }

    private fun showMovieDetail(movie: MovieDetailModel, reviews: List<ReviewModel>) {
        controller.setData(movie, reviews)
        activity?.title = movie.movieModel.title
    }
}