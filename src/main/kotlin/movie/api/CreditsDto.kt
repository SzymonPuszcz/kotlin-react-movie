package movie.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsDto(
    val cast: List<CastDto>,
)

@Serializable
data class CastDto(
    val name: String,
    val character: String,
    @SerialName("profile_path")
    val profilePath: String?,
)