package io.melbybaldove.investagramsexam.ui.login

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import dagger.android.support.DaggerAppCompatActivity
import io.melbybaldove.investagramsexam.R
import io.melbybaldove.presentation.ViewModelFactoryProvider
import io.melbybaldove.presentation.auth.AuthViewModel
import io.melbybaldove.presentation.movie.MovieViewModel
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity(), ViewModelFactoryProvider {
    @Inject
    override
    lateinit var authViewModelFactory: AuthViewModel.Factory
    @Inject
    override
    lateinit var movieViewModelFactory: MovieViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val navHost = NavHostFragment.create(R.navigation.login_nav_graph)
        supportFragmentManager.beginTransaction()
                .replace(R.id.login_nav_host_fragment, navHost)
                .setPrimaryNavigationFragment(navHost)
                .commit()
    }
}