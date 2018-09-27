package io.melbybaldove.commons

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
data class RequestOptions(val currentPage: Int = -1,
                          val pages: Int = -1,
                          val totalPages: Int = -1,
                          val invalidate: Boolean = false)