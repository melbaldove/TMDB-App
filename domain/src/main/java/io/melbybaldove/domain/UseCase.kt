package io.melbybaldove.domain

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */

interface UseCase<in REQUEST, out RESULT> {
    fun execute(request: REQUEST): RESULT
}