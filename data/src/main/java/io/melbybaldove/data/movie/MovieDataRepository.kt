package io.melbybaldove.data.movie

import io.melbybaldove.data.movie.entity.MovieEntityMapper
import io.melbybaldove.domain.movie.MovieRepository
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class MovieDataRepository @Inject constructor(private val remoteMovieSource: RemoteMovieSource) : MovieRepository {
    override fun getTrending() = remoteMovieSource.getTrending().map { it.map(MovieEntityMapper::map) }!!
}