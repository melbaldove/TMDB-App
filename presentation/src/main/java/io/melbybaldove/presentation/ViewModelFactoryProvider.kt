package io.melbybaldove.presentation

import io.melbybaldove.presentation.auth.AuthViewModel
import io.melbybaldove.presentation.movie.MovieViewModel

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
interface ViewModelFactoryProvider {
    val authViewModelFactory: AuthViewModel.Factory
    val movieViewModelFactory: MovieViewModel.Factory
}