package io.melbybaldove.domain.movie.usecase

import io.melbybaldove.commons.RequestOptions
import io.melbybaldove.domain.UseCase
import io.melbybaldove.domain.movie.MovieRepository
import io.melbybaldove.domain.movie.entity.Movie
import io.reactivex.Single

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class ShowTrending(private val movieRepository: MovieRepository) : UseCase<RequestOptions, Single<List<Movie>>> {
    override fun execute(request: RequestOptions): Single<List<Movie>> = movieRepository.getTrending(request)
}