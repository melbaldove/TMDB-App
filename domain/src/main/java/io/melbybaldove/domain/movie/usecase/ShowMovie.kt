package io.melbybaldove.domain.movie.usecase

import io.melbybaldove.domain.UseCase
import io.melbybaldove.domain.movie.MovieRepository
import io.melbybaldove.domain.movie.entity.Movie
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class ShowMovie @Inject constructor(private val movieRepository: MovieRepository) : UseCase<String, Single<Movie>> {
    override fun execute(request: String) = movieRepository.getMovie(request)
}