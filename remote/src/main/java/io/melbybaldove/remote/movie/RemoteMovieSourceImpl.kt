package io.melbybaldove.remote.movie

import io.melbybaldove.data.movie.RemoteMovieSource
import io.melbybaldove.data.movie.entity.MovieEntity
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class RemoteMovieSourceImpl @Inject constructor(private val movieApi: MovieApi) : RemoteMovieSource {
    override fun getTrending(): Single<List<MovieEntity>> = movieApi.fetchTrending().map { it.results }
}