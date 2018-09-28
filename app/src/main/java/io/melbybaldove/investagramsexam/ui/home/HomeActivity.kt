package io.melbybaldove.investagramsexam.ui.home

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment
import dagger.android.support.DaggerAppCompatActivity
import io.melbybaldove.investagramsexam.R
import io.melbybaldove.presentation.ViewModelFactoryProvider
import io.melbybaldove.presentation.auth.AuthViewModel
import io.melbybaldove.presentation.movie.MovieViewModel
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class HomeActivity : DaggerAppCompatActivity(), ViewModelFactoryProvider {
    @Inject
    override lateinit var movieViewModelFactory: MovieViewModel.Factory
    override val authViewModelFactory: AuthViewModel.Factory
        get() = throw IllegalStateException()

    override fun onCreate(savedInstanceState: Bundle?) {
        // becuz i wanna show splash for 1 second :p
        Thread.sleep(1000)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        }

        val navHost = NavHostFragment.create(R.navigation.nav_graph)
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_nav_host_fragment, navHost)
                .setPrimaryNavigationFragment(navHost)
                .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            drawer_layout.openDrawer(GravityCompat.START)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}