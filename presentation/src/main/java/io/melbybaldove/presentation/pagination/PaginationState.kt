package io.melbybaldove.presentation.pagination

import io.melbybaldove.commons.PaginatedResult

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class PaginationState(
        val page: Int,
        val totalResults: Int,
        val totalPages: Int
) {
    companion object {
        fun fromPaginatedResult(result: PaginatedResult<*>) = PaginationState(
                page = result.page,
                totalResults = result.totalResults,
                totalPages = result.totalPages
        )
    }
}