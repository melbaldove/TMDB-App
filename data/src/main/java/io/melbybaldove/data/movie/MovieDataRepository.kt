package io.melbybaldove.data.movie

import io.melbybaldove.commons.PaginatedResult
import io.melbybaldove.commons.RequestOptions
import io.melbybaldove.data.movie.entity.MovieEntityMapper
import io.melbybaldove.domain.movie.MovieRepository
import io.melbybaldove.domain.movie.entity.Movie
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class MovieDataRepository @Inject constructor(private val remoteMovieSource: RemoteMovieSource) : MovieRepository {
    override fun searchMovies(query: String, requestOptions: RequestOptions) = remoteMovieSource.searchMovies(
            query = query,
            requestOptions = requestOptions
    ).map {
        PaginatedResult(
                page = it.page,
                totalPages = it.totalPages,
                totalResults = it.totalResults,
                results = it.results.map(MovieEntityMapper::map)
        )
    }!!

    override fun getTrending() = remoteMovieSource.getTrending().map { it.map(MovieEntityMapper::map) }!!

    override fun getMovie(id: String) = remoteMovieSource.getMovie(id).map(MovieEntityMapper::map)!!

    override fun rateMovie(id: String, rating: Float): Completable = remoteMovieSource.rateMovie(id, rating)

    override fun deleteRating(id: String) = remoteMovieSource.deleteRating(id)
}