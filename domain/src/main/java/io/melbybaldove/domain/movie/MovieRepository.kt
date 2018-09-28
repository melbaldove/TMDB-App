package io.melbybaldove.domain.movie

import io.melbybaldove.domain.movie.entity.Movie
import io.reactivex.Single

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
interface MovieRepository {
    fun getTrending(): Single<List<Movie>>
}