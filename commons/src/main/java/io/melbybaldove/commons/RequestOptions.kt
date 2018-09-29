package io.melbybaldove.commons

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
data class RequestOptions(val page: Int = -1,
                          val limit: Int = -1,
                          val invalidate: Boolean = false)