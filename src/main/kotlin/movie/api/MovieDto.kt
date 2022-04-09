package movie.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val title: String,
    val overview: String,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("poster_path")
    val posterPath: String?,
    val runtime: Int?,
    val genres: List<GenreDto>,
)

@Serializable
data class GenreDto(
    val name: String,
)
