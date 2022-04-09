package movie.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PopularDto(
    val id: Long,
    val title: String,
    @SerialName("poster_path")
    val posterPath: String?
)