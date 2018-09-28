package io.melbybaldove.authentication

import io.reactivex.Single

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
interface Authenticator {
    fun authenticateWith(username: String, password: String): Single<String>
}