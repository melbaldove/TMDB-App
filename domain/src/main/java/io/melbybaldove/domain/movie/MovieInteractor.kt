package io.melbybaldove.domain.movie

import io.melbybaldove.domain.movie.usecase.ShowTrending
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class MovieInteractor @Inject constructor(val showTrending: ShowTrending)