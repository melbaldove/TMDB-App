package io.melbybaldove.domain.movie.usecase

import io.kotlintest.Description
import io.kotlintest.specs.StringSpec
import io.melbybaldove.commons.RequestOptions
import io.melbybaldove.domain.movie.MovieRepository
import io.melbybaldove.domain.movie.entity.Movie
import io.melbybaldove.domain.movie.entity.SearchMoviesRequest
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.observers.TestObserver

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class SearchMoviesTest : StringSpec() {
    private lateinit var testObserver: TestObserver<List<Movie>>
    private lateinit var movieRepository: MovieRepository
    private lateinit var searchMovies: SearchMovies

    override fun beforeTest(description: Description) {
        super.beforeTest(description)
        movieRepository = mockk()
        searchMovies = SearchMovies(movieRepository)
    }

    init {
        "given a query, when execute() should emit matched movies" {
            val someQuery = "someQuery"
            val someMatchedResults = (1..5).map { Movie.createTestMovie() }
            val requestOptions = RequestOptions()
            every { movieRepository.searchMovies(someQuery, requestOptions) } returns Single.just(someMatchedResults)

            testObserver = searchMovies.execute(SearchMoviesRequest(someQuery, requestOptions)).test()

            testObserver.assertValue(someMatchedResults)
        }
    }
}