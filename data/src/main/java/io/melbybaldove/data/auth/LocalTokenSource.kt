package io.melbybaldove.data.auth

import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
interface LocalTokenSource {
    fun saveToken(token: Token): Single<Token>
    fun deleteToken(): Completable
    fun getToken(): Single<Token>
}