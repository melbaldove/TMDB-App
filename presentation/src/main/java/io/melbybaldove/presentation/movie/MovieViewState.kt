package io.melbybaldove.presentation.movie

import com.airbnb.mvrx.MvRxState
import io.melbybaldove.commons.ErrorModel
import io.melbybaldove.commons.LoadingOptions
import io.melbybaldove.presentation.movie.model.MovieModel

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
data class MovieViewState(
        val loadingOptions: LoadingOptions = LoadingOptions(false),
        val error: ErrorModel? = null,
        val movies: List<MovieModel>? = null,
        val query: String = "",
        val screenState: ScreenState = ScreenState.TRENDING
) : MvRxState

enum class ScreenState {
    TRENDING,
    SEARCH
}