package io.melbybaldove.presentation.movie.detail

import com.airbnb.mvrx.MvRxState
import io.melbybaldove.commons.ErrorModel
import io.melbybaldove.commons.LoadingOptions
import io.melbybaldove.presentation.movie.detail.model.MovieDetailModel
import io.melbybaldove.presentation.movie.detail.model.ReviewModel

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
data class MovieDetailState(
        val loadingOptions: LoadingOptions = LoadingOptions(isLoading = false),
        val errorModel: ErrorModel? = null,
        val movie: MovieDetailModel? = null,
        val reviews: List<ReviewModel>? = null
) : MvRxState