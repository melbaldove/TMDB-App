package io.melbybaldove.domain.movie

import io.melbybaldove.domain.movie.usecase.*
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class MovieInteractor @Inject constructor(val showTrending: ShowTrending,
                                          val searchMovies: SearchMovies,
                                          val showMovie: ShowMovie,
                                          val rateMovie: RateMovie,
                                          val deleteRating: DeleteRating)