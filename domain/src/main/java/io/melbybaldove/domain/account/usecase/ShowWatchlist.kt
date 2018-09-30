package io.melbybaldove.domain.account.usecase

import io.melbybaldove.domain.NoArgsUseCase
import io.melbybaldove.domain.account.AccountRepository
import io.melbybaldove.domain.movie.entity.Movie
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class ShowWatchlist @Inject constructor(private val accountRepository: AccountRepository) : NoArgsUseCase<Single<List<Movie>>> {
    override fun execute() = accountRepository.getWatchlist()
}