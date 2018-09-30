package io.melbybaldove.domain.account.usecase

import io.melbybaldove.domain.UseCase
import io.melbybaldove.domain.account.AccountRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class RemoveFromWatchlist @Inject constructor(private val accountRepository: AccountRepository) : UseCase<String, Completable> {
    override fun execute(request: String) = accountRepository.removeFromWatchlist(request)
}