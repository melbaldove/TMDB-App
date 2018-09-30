package io.melbybaldove.domain.movie.usecase

import io.melbybaldove.domain.UseCase
import io.melbybaldove.domain.movie.MovieRepository
import io.melbybaldove.domain.movie.entity.RateMovieRequest
import io.reactivex.Completable
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class RateMovie @Inject constructor(private val movieRepository: MovieRepository) : UseCase<RateMovieRequest, Completable> {
    override fun execute(request: RateMovieRequest) = movieRepository.rateMovie(request.movieId, request.rating)
}