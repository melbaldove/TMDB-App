package io.melbybaldove.investagramsexam.ui.login.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.melbybaldove.investagramsexam.dagger.scope.PerFragment
import io.melbybaldove.investagramsexam.ui.login.LoginFragment

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
@Module
abstract class LoginFragmentProvider {
    @PerFragment
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun bindLoginFragment(): LoginFragment
}