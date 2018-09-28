package io.melbybaldove.investagramsexam.dagger

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.melbybaldove.investagramsexam.InvestagramsExamApplication
import io.melbybaldove.investagramsexam.authentication.AuthenticationModule
import io.melbybaldove.investagramsexam.data.DataModule
import io.melbybaldove.investagramsexam.local.LocalModule
import io.melbybaldove.presentation.di.PresentationAssistedModule
import javax.inject.Singleton

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class),
    (ApplicationModule::class), (ActivityBuilder::class), (PresentationAssistedModule::class),
    (AuthenticationModule::class), (NetworkModule::class), (LocalModule::class), (DataModule::class)])
interface ApplicationComponent : AndroidInjector<InvestagramsExamApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AndroidInjector<InvestagramsExamApplication>
    }
}