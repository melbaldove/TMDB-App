package io.melbybaldove.presentation.movie

import android.support.v4.app.FragmentActivity
import com.airbnb.mvrx.*
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.melbybaldove.commons.ErrorModel
import io.melbybaldove.commons.LoadingOptions
import io.melbybaldove.commons.RequestOptions
import io.melbybaldove.commons.rx.SchedulerProvider
import io.melbybaldove.domain.movie.MovieInteractor
import io.melbybaldove.domain.movie.entity.SearchMoviesRequest
import io.melbybaldove.presentation.ViewModelFactoryProvider
import io.melbybaldove.presentation.movie.model.MovieModelMapper
import io.melbybaldove.presentation.pagination.PaginationState

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class MovieViewModel @AssistedInject constructor(
        @Assisted initialState: MovieViewState,
        private val schedulerProvider: SchedulerProvider,
        private val movieInteractor: MovieInteractor)
    : BaseMvRxViewModel<MovieViewState>(initialState = initialState, debugMode = BuildConfig.DEBUG) {
    @AssistedInject.Factory
    interface Factory {
        fun create(initialState: MovieViewState): MovieViewModel
    }

    companion object : MvRxViewModelFactory<MovieViewState> {
        @JvmStatic
        override fun create(activity: FragmentActivity, state: MovieViewState) =
                (activity as ViewModelFactoryProvider).movieViewModelFactory.create(state)
    }

    private var paginationState: PaginationState? = null

    fun loadTrending() = movieInteractor.showTrending.execute()
            .compose(schedulerProvider.ioToUi())
            .execute {
                when (it) {
                    is Loading -> copy(loadingOptions = LoadingOptions(true))
                    is Success -> copy(
                            loadingOptions = LoadingOptions(isLoading = false),
                            movies = it()!!.map(MovieModelMapper::map),
                            screenState = ScreenState.TRENDING
                    )
                    is Fail -> copy(loadingOptions = LoadingOptions(false),
                            error = ErrorModel(message = it.error.localizedMessage))
                    else -> this
                }
            }

    fun searchMovies(query: String, loadMore: Boolean = false) {
        val requestOptions = if (loadMore && hasMoreToLoad()) {
            RequestOptions(page = paginationState!!.page + 1)
        } else {
            RequestOptions(page = 1)
        }
        movieInteractor.searchMovies
                .execute(request = SearchMoviesRequest(query = query, requestOptions = requestOptions))
                .compose(schedulerProvider.ioToUi())
                .execute {
                    when (it) {
                        is Loading -> copy(loadingOptions = LoadingOptions(true))
                        is Success -> {
                            val paginatedResult = it()!!
                            paginationState = PaginationState.fromPaginatedResult(paginatedResult)
                            copy(loadingOptions = LoadingOptions(false),
                                    movies = paginatedResult.results.map(MovieModelMapper::map),
                                    screenState = ScreenState.SEARCH,
                                    query = query)
                        }
                        is Fail -> copy(loadingOptions = LoadingOptions(false),
                                error = ErrorModel(message = it.error.localizedMessage))
                        else -> this
                    }
                }
    }

    fun loadMoreSearchResults() {
        // TODO Handle errors for this
        withState {
            searchMovies(it.query, true)
        }
    }

    fun hasMoreToLoad() = paginationState?.let { it.page < it.totalPages } ?: false
}
