package io.melbybaldove.remote.movie

import io.melbybaldove.commons.PaginatedResult
import io.melbybaldove.commons.RequestOptions
import io.melbybaldove.data.movie.RemoteMovieSource
import io.melbybaldove.data.movie.entity.MovieEntity
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class RemoteMovieSourceImpl @Inject constructor(private val movieApi: MovieApi) : RemoteMovieSource {
    override fun getTrending(): Single<List<MovieEntity>> = movieApi.fetchTrending().map { it.results }
    override fun searchMovies(query: String, requestOptions: RequestOptions) = movieApi
            .searchMovies(query, requestOptions.page)
            .map {
                PaginatedResult(
                        page = it.page,
                        totalResults = it.total_results,
                        totalPages = it.total_pages,
                        results = it.results
                )
            }!!
}