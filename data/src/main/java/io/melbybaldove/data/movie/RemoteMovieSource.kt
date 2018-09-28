package io.melbybaldove.data.movie

import io.melbybaldove.data.movie.entity.MovieEntity
import io.reactivex.Single

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
interface RemoteMovieSource {
    fun getTrending(): Single<List<MovieEntity>>
}