package io.melbybaldove.investagramsexam.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.airbnb.mvrx.MvRxViewModelStore
import com.airbnb.mvrx.MvRxViewModelStoreOwner
import dagger.android.support.DaggerAppCompatActivity
import io.melbybaldove.authentication.Authenticator
import io.melbybaldove.investagramsexam.R
import io.melbybaldove.presentation.ViewModelFactoryProvider
import io.melbybaldove.presentation.auth.AuthViewModel
import io.melbybaldove.presentation.movie.MovieViewModel
import io.melbybaldove.presentation.movie.detail.MovieDetailViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class HomeActivity : DaggerAppCompatActivity(), ViewModelFactoryProvider, MvRxViewModelStoreOwner {
    @Inject
    override lateinit var movieViewModelFactory: MovieViewModel.Factory
    @Inject
    override lateinit var movieDetailViewModelFactory: MovieDetailViewModel.Factory
    override val authViewModelFactory: AuthViewModel.Factory
        get() = throw IllegalStateException()

    @Inject
    lateinit var authenticator: Authenticator
    override val mvrxViewModelStore: MvRxViewModelStore by lazy { MvRxViewModelStore(viewModelStore) }

    private val disposables = CompositeDisposable()

    private var currentDestination: Int? = null
    private lateinit var navHost: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        // becuz i wanna show splash for 1 second :p
        Thread.sleep(1000)
        setTheme(R.style.AppTheme)
        mvrxViewModelStore.restoreViewModels(this, savedInstanceState)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        }
        nav_view.setNavigationItemSelectedListener {
            drawer_layout.closeDrawers()
            if (currentDestination == it.itemId) return@setNavigationItemSelectedListener true
            currentDestination = it.itemId
            it.isChecked

            when (it.itemId) {
                R.id.drawer_item_logout -> logout()
                R.id.drawer_item_trending -> {
                    navigateTo(R.id.homeFragment)
                }
                R.id.drawer_item_watchlist -> navigateTo(R.id.watchlistFragment)
            }

            true
        }

        navHost = NavHostFragment.create(R.navigation.nav_graph)
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_nav_host_fragment, navHost)
                .setPrimaryNavigationFragment(navHost)
                .commit()

        currentDestination = R.id.drawer_item_trending
    }

    override fun onStart() {
        super.onStart()
        if (!authenticator.isAuthenticated()) {
            navigateTo(R.id.loginActivity)
            finish()
        }
    }

    private fun navigateTo(id: Int) {
        navHost.navController.popBackStack()
        navHost.navController.navigate(id)
    }

    private fun logout() {
        navigateTo(R.id.loginActivity)
        authenticator.logout().subscribe().addTo(disposables)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            drawer_layout.openDrawer(GravityCompat.START)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    override fun onSupportNavigateUp() = findNavController(R.id.main_nav_host_fragment).navigateUp()

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }
    }
}