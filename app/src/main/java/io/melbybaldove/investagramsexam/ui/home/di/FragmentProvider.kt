package io.melbybaldove.investagramsexam.ui.home.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.melbybaldove.investagramsexam.dagger.scope.PerFragment
import io.melbybaldove.investagramsexam.ui.home.HomeFragment
import io.melbybaldove.investagramsexam.ui.moviedetail.MovieDetailFragment
import io.melbybaldove.investagramsexam.ui.watchlist.WatchlistFragment

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
@Module
abstract class FragmentProvider {
    @PerFragment
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun bindHomeFragment(): HomeFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun bindMovieDetailFragment(): MovieDetailFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun bindWatchlistFragment(): WatchlistFragment
}