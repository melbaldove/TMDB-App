package io.melbybaldove.domain

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */

interface NoArgsUseCase<out RESULT> {
    fun execute(): RESULT
}