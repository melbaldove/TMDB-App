package io.melbybaldove.presentation.auth

import com.airbnb.mvrx.MvRxState
import io.melbybaldove.commons.ErrorModel
import io.melbybaldove.commons.LoadingOptions

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
data class AuthState(
        val loadingOptions: LoadingOptions = LoadingOptions(false),
        val error: ErrorModel? = null,
        val isLoginSuccess: Boolean? = null
) : MvRxState