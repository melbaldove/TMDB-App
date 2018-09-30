package io.melbybaldove.domain.movie.usecase

import io.melbybaldove.domain.UseCase
import io.melbybaldove.domain.movie.MovieRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class DeleteRating @Inject constructor(private val movieRepository: MovieRepository) : UseCase<String, Completable> {
    override fun execute(request: String) = movieRepository.deleteRating(request)
}