package io.melbybaldove.data.movie.entity

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */

data class CreditsEntity(
        val cast: List<CastEntity>,
        val crew: List<CrewEntity>
)

data class CrewEntity(
        val credit_id: String,
        val department: String,
        val gender: Int,
        val id: Int,
        val job: String,
        val name: String,
        val profile_path: String
)

data class CastEntity(
        val cast_id: Int,
        val character: String,
        val credit_id: String,
        val gender: Int,
        val id: Int,
        val name: String,
        val order: Int,
        val profile_path: String
)