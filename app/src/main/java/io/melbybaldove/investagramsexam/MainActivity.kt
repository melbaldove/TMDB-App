package io.melbybaldove.investagramsexam

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import dagger.android.support.DaggerAppCompatActivity
import io.melbybaldove.presentation.ViewModelFactoryProvider
import io.melbybaldove.presentation.auth.AuthViewModel
import io.melbybaldove.presentation.movie.MovieViewModel
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), ViewModelFactoryProvider {
    @Inject
    override
    lateinit var authViewModelFactory: AuthViewModel.Factory
    @Inject
    override
    lateinit var movieViewModelFactory: MovieViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        // becuz i wanna show splash for 1 second :p
        Thread.sleep(1000)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHost = NavHostFragment.create(R.navigation.nav_graph)
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_nav_host_fragment, navHost)
                .setPrimaryNavigationFragment(navHost)
                .commit()
    }
}
