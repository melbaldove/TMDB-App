package io.melbybaldove.presentation.movie.detail

import android.support.v4.app.FragmentActivity
import com.airbnb.mvrx.*
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.melbybaldove.commons.ErrorModel
import io.melbybaldove.commons.LoadingOptions
import io.melbybaldove.commons.rx.SchedulerProvider
import io.melbybaldove.domain.movie.MovieInteractor
import io.melbybaldove.domain.movie.entity.RateMovieRequest
import io.melbybaldove.presentation.BuildConfig
import io.melbybaldove.presentation.ViewModelFactoryProvider
import io.melbybaldove.presentation.movie.detail.model.MovieDetailModelMapper
import io.melbybaldove.presentation.movie.detail.model.ReviewModel
import io.melbybaldove.presentation.movie.model.MovieModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class MovieDetailViewModel @AssistedInject constructor(
        @Assisted initialState: MovieDetailState,
        private val schedulerProvider: SchedulerProvider,
        private val movieInteractor: MovieInteractor) :
        BaseMvRxViewModel<MovieDetailState>(initialState = initialState, debugMode = BuildConfig.DEBUG) {
    @AssistedInject.Factory
    interface Factory {
        fun create(initialState: MovieDetailState): MovieDetailViewModel
    }

    private val disposables = CompositeDisposable()

    companion object : MvRxViewModelFactory<MovieDetailState> {
        @JvmStatic
        override fun create(activity: FragmentActivity, state: MovieDetailState) =
                (activity as ViewModelFactoryProvider).movieDetailViewModelFactory.create(state)
    }

    fun showMovie(id: String) = movieInteractor.showMovie.execute(id)
            .compose(schedulerProvider.ioToUi())
            .execute {
                when (it) {
                    is Loading -> copy(loadingOptions = LoadingOptions(true))
                    is Success -> {
                        copy(loadingOptions = LoadingOptions(isLoading = false),
                                movie = MovieDetailModelMapper.map(it()!!),
                                reviews = it()!!.reviews.map { review ->
                                    ReviewModel(
                                            id = review.id,
                                            author = review.author,
                                            content = review.content
                                    )
                                })
                    }
                    is Fail -> copy(loadingOptions = LoadingOptions(false),
                            errorModel = ErrorModel(message = it.error.localizedMessage))
                    else -> this
                }
            }

    fun rate(movieModel: MovieModel, rating: Float) {
        movieInteractor.rateMovie
                .execute(RateMovieRequest(movieModel.id, rating))
                .compose(schedulerProvider.ioToUi<Void>())
                .subscribe({}, {
                    setState {
                        copy(errorModel = ErrorModel(message = it.localizedMessage))
                    }
                }).addTo(disposables)
    }

    fun deleteRating(movieModel: MovieModel) {
        movieInteractor.deleteRating
                .execute(movieModel.id)
                .compose(schedulerProvider.ioToUi<Void>())
                .subscribe({}, {
                    setState {
                        copy(errorModel = ErrorModel(message = it.localizedMessage))
                    }
                }).addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}