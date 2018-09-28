package io.melbybaldove.commons

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
data class ErrorModel(
        val title: String = "Oops!",
        val message: String = "Sorry. Something went wrong. We are now fixing the issue."
)