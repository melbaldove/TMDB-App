package io.melbybaldove.investagramsexam.ui.login

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import dagger.android.support.DaggerAppCompatActivity
import io.melbybaldove.investagramsexam.R
import io.melbybaldove.presentation.ViewModelFactoryProvider
import io.melbybaldove.presentation.auth.AuthViewModel
import io.melbybaldove.presentation.movie.MovieViewModel
import io.melbybaldove.presentation.movie.detail.MovieDetailViewModel
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity(), ViewModelFactoryProvider, LoginFragment.LoginDoneCallback {
    override val movieDetailViewModelFactory: MovieDetailViewModel.Factory
        get() = throw IllegalStateException()
    @Inject
    override
    lateinit var authViewModelFactory: AuthViewModel.Factory

    override val movieViewModelFactory: MovieViewModel.Factory
        get() = throw IllegalStateException()

    private lateinit var navHost: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        navHost = NavHostFragment.create(R.navigation.login_nav_graph)
        supportFragmentManager.beginTransaction()
                .replace(R.id.login_nav_host_fragment, navHost)
                .setPrimaryNavigationFragment(navHost)
                .commit()
    }

    override fun onDoneLoggingIn() {
        navHost.navController.navigate(R.id.action_navigateToHome)
        finish()
    }

}
