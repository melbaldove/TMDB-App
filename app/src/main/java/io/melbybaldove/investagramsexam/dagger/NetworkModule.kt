package io.melbybaldove.investagramsexam.dagger

import dagger.Module
import dagger.Provides
import io.melbybaldove.investagramsexam.BuildConfig
import io.melbybaldove.investagramsexam.remote.ApiKeyInterceptor
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(apiKeyInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { Timber.d(it) }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
}