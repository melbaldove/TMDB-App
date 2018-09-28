package io.melbybaldove.investagramsexam.ui.home.di

import dagger.Module
import dagger.Provides
import io.melbybaldove.investagramsexam.dagger.scope.PerFragment
import io.melbybaldove.investagramsexam.ui.home.HomeActivity
import io.melbybaldove.investagramsexam.ui.util.DialogHelper
import io.melbybaldove.investagramsexam.ui.util.DialogHelperImpl

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
@Module
class HomeModule {
    @PerFragment
    @Provides
    fun provideDialogHelper(activity: HomeActivity): DialogHelper = DialogHelperImpl(activity)
}