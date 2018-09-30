package io.melbybaldove.presentation

import io.melbybaldove.presentation.auth.AuthViewModel
import io.melbybaldove.presentation.movie.MovieViewModel
import io.melbybaldove.presentation.movie.detail.MovieDetailViewModel

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
interface ViewModelFactoryProvider {
    val authViewModelFactory: AuthViewModel.Factory
    val movieViewModelFactory: MovieViewModel.Factory
    val movieDetailViewModelFactory: MovieDetailViewModel.Factory
}