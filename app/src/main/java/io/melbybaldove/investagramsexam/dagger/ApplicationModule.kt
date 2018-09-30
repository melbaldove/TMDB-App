package io.melbybaldove.investagramsexam.dagger

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.melbybaldove.commons.rx.SchedulerProvider
import io.melbybaldove.data.movie.entity.Rated
import io.melbybaldove.investagramsexam.BuildConfig
import io.melbybaldove.remote.movie.response.RatedDeserializer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
@Module
class ApplicationModule {
    @Singleton
    @Provides
    fun provideApplicationContext(application: Application): Context = application

    @Singleton
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = object : SchedulerProvider {
        override fun io(): Scheduler = Schedulers.io()
        override fun ui(): Scheduler = AndroidSchedulers.mainThread()
        override fun computation() = Schedulers.computation()
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().registerTypeAdapter(Rated::class.java, RatedDeserializer())
            .create()
}