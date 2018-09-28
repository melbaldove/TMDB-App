package io.melbybaldove.presentation.movie

import android.support.v4.app.FragmentActivity
import com.airbnb.mvrx.*
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.melbybaldove.commons.LoadingOptions
import io.melbybaldove.commons.rx.SchedulerProvider
import io.melbybaldove.domain.movie.MovieInteractor
import io.melbybaldove.presentation.ViewModelFactoryProvider
import io.melbybaldove.presentation.movie.model.MovieModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class MovieViewModel @AssistedInject constructor(
        @Assisted initialState: MovieViewState,
        private val schedulerProvider: SchedulerProvider,
        private val movieInteractor: MovieInteractor)
    : BaseMvRxViewModel<MovieViewState>(initialState = initialState, debugMode = BuildConfig.DEBUG) {

    private val df = SimpleDateFormat("MMM dd, yyy", Locale.US)
    @AssistedInject.Factory
    interface Factory {
        fun create(initialState: MovieViewState): MovieViewModel
    }

    companion object : MvRxViewModelFactory<MovieViewState> {
        @JvmStatic
        override fun create(activity: FragmentActivity, state: MovieViewState) =
                (activity as ViewModelFactoryProvider).movieViewModelFactory.create(state)
    }

    fun loadTrending() = movieInteractor.showTrending.execute()
            .compose(schedulerProvider.ioToUi())
            .execute {
                when (it) {
                    is Loading -> copy(loadingOptions = LoadingOptions(true))
                    is Success -> copy(
                            loadingOptions = LoadingOptions(isLoading = false),
                            movies = it()!!.map { movie ->
                                MovieModel(id = movie.id,
                                        title = movie.title,
                                        poster = movie.poster,
                                        date = df.format(movie.date),
                                        desc = movie.description)
                            }
                    )
                    is Fail -> this
                    else -> this
                }
            }
}