package io.melbybaldove.investagramsexam.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.melbybaldove.investagramsexam.dagger.scope.PerActivity
import io.melbybaldove.investagramsexam.ui.home.HomeActivity
import io.melbybaldove.investagramsexam.ui.home.di.HomeFragmentProvider
import io.melbybaldove.investagramsexam.ui.login.LoginActivity
import io.melbybaldove.investagramsexam.ui.login.di.LoginFragmentProvider

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
@Module
abstract class ActivityBuilder {
    @PerActivity
    @ContributesAndroidInjector(modules = [LoginFragmentProvider::class])
    abstract fun bindLoginActivity(): LoginActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [HomeFragmentProvider::class])
    abstract fun bindHomeActivity(): HomeActivity
}