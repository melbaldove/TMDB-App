package io.melbybaldove.data.auth

import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class TokenRepository @Inject constructor(private val localTokenSource: LocalTokenSource) {
    fun saveToken(token: String) = localTokenSource.saveToken(Token(token))

    fun evictToken() = localTokenSource.deleteToken()

    fun getToken() = localTokenSource.getToken()
}