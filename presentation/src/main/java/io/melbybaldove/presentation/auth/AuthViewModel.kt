package io.melbybaldove.presentation.auth

import android.support.v4.app.FragmentActivity
import com.airbnb.mvrx.*
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.melbybaldove.authentication.Authenticator
import io.melbybaldove.commons.ErrorModel
import io.melbybaldove.commons.LoadingOptions
import io.melbybaldove.commons.rx.SchedulerProvider
import io.melbybaldove.presentation.ViewModelFactoryProvider

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class AuthViewModel @AssistedInject constructor(
        @Assisted initialState: AuthState,
        private val schedulerProvider: SchedulerProvider,
        private val authentication: Authenticator) : BaseMvRxViewModel<AuthState>(initialState, debugMode = BuildConfig.DEBUG) {
    @AssistedInject.Factory
    interface Factory {
        fun create(initialState: AuthState): AuthViewModel
    }

    companion object : MvRxViewModelFactory<AuthState> {
        @JvmStatic
        override fun create(activity: FragmentActivity, state: AuthState) = (activity as ViewModelFactoryProvider)
                .authViewModelFactory.create(initialState = state)
    }

    fun signin(username: String, password: String) = authentication.authenticateWith(username, password)
            .compose(schedulerProvider.ioToUi())
            .execute {
                when (it) {
                    is Loading -> copy(loadingOptions = LoadingOptions(true, title = "Logging you in..."))
                    is Success -> copy(loadingOptions = LoadingOptions(false),
                            isLoginSuccess = true)
                    is Fail -> copy(loadingOptions = LoadingOptions(false),
                            error = ErrorModel(message = it.error.localizedMessage))
                    else -> this
                }
            }
}