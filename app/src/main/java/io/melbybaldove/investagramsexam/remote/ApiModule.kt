package io.melbybaldove.investagramsexam.remote

import dagger.Module
import dagger.Provides
import io.melbybaldove.remote.ApiClient
import io.melbybaldove.remote.ApiClientImpl
import io.melbybaldove.remote.account.AccountApi
import io.melbybaldove.remote.movie.MovieApi
import javax.inject.Singleton

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
@Module
class ApiModule {
    @Singleton
    @Provides
    fun provideApiClient(apiClientImpl: ApiClientImpl): ApiClient = apiClientImpl

    @Singleton
    @Provides
    fun provideMovieApi(apiClient: ApiClient): MovieApi = apiClient.movie

    @Singleton
    @Provides
    fun provideAccountApi(apiClient: ApiClient): AccountApi = apiClient.account
}