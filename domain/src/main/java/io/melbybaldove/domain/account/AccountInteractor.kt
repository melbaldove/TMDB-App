package io.melbybaldove.domain.account

import io.melbybaldove.domain.account.usecase.AddToWatchlist
import io.melbybaldove.domain.account.usecase.RemoveFromWatchlist
import io.melbybaldove.domain.account.usecase.ShowWatchlist
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class AccountInteractor @Inject constructor(val showWatchlist: ShowWatchlist,
                                            val addToWatchlist: AddToWatchlist,
                                            val removeFromWatchlist: RemoveFromWatchlist)