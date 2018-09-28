package io.melbybaldove.investagramsexam.remote

import dagger.Module
import dagger.Provides
import io.melbybaldove.data.movie.RemoteMovieSource
import io.melbybaldove.remote.movie.RemoteMovieSourceImpl
import javax.inject.Singleton

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
@Module(includes = [ApiModule::class])
class RemoteModule {
    @Singleton
    @Provides
    fun provideRemoteMovieSource(remoteMovieSourceImpl: RemoteMovieSourceImpl): RemoteMovieSource = remoteMovieSourceImpl
}