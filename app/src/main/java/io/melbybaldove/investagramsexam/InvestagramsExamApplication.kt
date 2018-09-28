package io.melbybaldove.investagramsexam

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.melbybaldove.investagramsexam.dagger.DaggerApplicationComponent
import timber.log.Timber

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class InvestagramsExamApplication : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder()
                .application(this)
                .build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}