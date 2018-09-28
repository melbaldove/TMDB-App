package io.melbybaldove.investagramsexam.data

import dagger.Module
import dagger.Provides
import io.melbybaldove.data.movie.MovieDataRepository
import io.melbybaldove.domain.movie.MovieRepository
import javax.inject.Singleton

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
@Module
class DataModule {
    @Singleton
    @Provides
    fun provideMovieRepository(movieDataRepository: MovieDataRepository): MovieRepository = movieDataRepository
}