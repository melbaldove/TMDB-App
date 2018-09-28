package io.melbybaldove.investagramsexam.ui.login.di

import dagger.Module
import dagger.Provides
import io.melbybaldove.investagramsexam.MainActivity
import io.melbybaldove.investagramsexam.dagger.scope.PerFragment
import io.melbybaldove.investagramsexam.ui.util.DialogHelper
import io.melbybaldove.investagramsexam.ui.util.DialogHelperImpl

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
@Module
class LoginModule {
    @PerFragment
    @Provides
    fun provideDialogHelper(activity: MainActivity): DialogHelper = DialogHelperImpl(activity)
}