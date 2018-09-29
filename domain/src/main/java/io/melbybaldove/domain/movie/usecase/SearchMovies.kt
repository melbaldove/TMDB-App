package io.melbybaldove.domain.movie.usecase

import io.melbybaldove.commons.PaginatedResult
import io.melbybaldove.domain.UseCase
import io.melbybaldove.domain.movie.MovieRepository
import io.melbybaldove.domain.movie.entity.Movie
import io.melbybaldove.domain.movie.entity.SearchMoviesRequest
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class SearchMovies @Inject constructor(private val movieRepository: MovieRepository) :
        UseCase<SearchMoviesRequest, Single<PaginatedResult<Movie>>> {
    override fun execute(request: SearchMoviesRequest) = movieRepository.searchMovies(
            query = request.query,
            requestOptions = request.requestOptions
    )
}