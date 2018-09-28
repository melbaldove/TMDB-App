package io.melbybaldove.data.movie

import io.melbybaldove.domain.movie.MovieRepository
import io.melbybaldove.domain.movie.entity.Movie
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class MovieDataRepository @Inject constructor() : MovieRepository {
    override fun getTrending(): Single<List<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}