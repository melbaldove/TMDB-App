package io.melbybaldove.commons

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
data class PaginatedResult<RESULT>(
        val page: Int,
        val totalResults: Int,
        val totalPages: Int,
        val results: List<RESULT>
)