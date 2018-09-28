package io.melbybaldove.domain.movie.usecase

import io.melbybaldove.domain.NoArgsUseCase
import io.melbybaldove.domain.movie.MovieRepository
import io.melbybaldove.domain.movie.entity.Movie
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class ShowTrending @Inject constructor(private val movieRepository: MovieRepository) : NoArgsUseCase<Single<List<Movie>>> {
    override fun execute(): Single<List<Movie>> = movieRepository.getTrending()
}