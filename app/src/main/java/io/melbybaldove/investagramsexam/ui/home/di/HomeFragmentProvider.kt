package io.melbybaldove.investagramsexam.ui.home.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.melbybaldove.investagramsexam.dagger.scope.PerFragment
import io.melbybaldove.investagramsexam.ui.home.HomeFragment

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
@Module
abstract class HomeFragmentProvider {
    @PerFragment
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun bindHomeFragment(): HomeFragment
}