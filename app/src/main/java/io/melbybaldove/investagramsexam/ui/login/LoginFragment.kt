package io.melbybaldove.investagramsexam.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import io.melbybaldove.investagramsexam.R
import io.melbybaldove.investagramsexam.dagger.BaseMvRxDaggerFragment
import io.melbybaldove.presentation.auth.AuthViewModel
import io.melbybaldove.investagramsexam.ui.util.DialogHelper
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class LoginFragment : BaseMvRxDaggerFragment() {
    private val authViewModel: AuthViewModel by fragmentViewModel()
    @Inject
    lateinit var dialogHelper: DialogHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_login_login.setOnClickListener {
            authViewModel.signin(
                    username = fragment_login_username.text.toString(),
                    password = fragment_login_password.text.toString()
            )
        }
    }

    override fun invalidate() {
        withState(authViewModel) { state ->
            dialogHelper.shouldShowLoading(state.loadingOptions)
            if (state.loadingOptions.isLoading) return@withState

            state.error?.let {
                dialogHelper.showError(it)
                return@withState
            }

            state.isLoginSuccess ?: return@withState
            if (state.isLoginSuccess!!) {
                proceedToMainScreen()
            }
        }
    }

    private fun proceedToMainScreen() {
        fragmentManager?.popBackStack()
    }
}