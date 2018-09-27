package io.melbybaldove.domain.movie.usecase;

import io.kotlintest.Description
import io.kotlintest.specs.StringSpec
import io.melbybaldove.commons.RequestOptions
import io.melbybaldove.domain.movie.MovieRepository
import io.melbybaldove.domain.movie.entity.Movie
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.observers.TestObserver

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class ShowTrendingTest : StringSpec() {
    private lateinit var testObserver: TestObserver<List<Movie>>
    private lateinit var movieRepository: MovieRepository
    private lateinit var showTrending: ShowTrending

    override fun beforeTest(description: Description) {
        super.beforeTest(description)
        movieRepository = mockk()
        showTrending = ShowTrending(movieRepository)
    }

    init {
        "given when execute() should return list of trending movies" {
            val trendingMovies = (1..5).map { Movie.createTestMovie() }
            every { movieRepository.getTrending(RequestOptions()) } returns Single.just(trendingMovies)

            testObserver = showTrending.execute(RequestOptions()).test()

            testObserver.assertValue(trendingMovies)
        }
    }
}