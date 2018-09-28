package io.melbybaldove.data.auth

import io.melbybaldove.data.auth.Token
import io.reactivex.Single

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
interface RemoteTokenSource {
    fun exchangeToken(token: String): Single<Token>
}