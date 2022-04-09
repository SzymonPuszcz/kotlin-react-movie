package movie

import kotlinx.serialization.json.Json
import movie.Global.IMAGE_BASE_URL

object Global {
    const val API_KEY = "9582e8f9dc236fc197f52c2622e66591"
    const val BASE_URL = "https://api.themoviedb.org/3"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p"

    val Json = Json {
        ignoreUnknownKeys = true
    }
}

fun String?.toPosterUrl() =
    this?.let {
        "$IMAGE_BASE_URL/w300_and_h450_bestv2$it"
    } ?: ""

fun String?.toBackdropUrl() =
    this?.let {
        "$IMAGE_BASE_URL/w1920_and_h800_multi_faces$it"
    } ?: ""

fun String?.toProfileUrl() =
    this?.let {
        "$IMAGE_BASE_URL/w276_and_h350_face$it"
    } ?: "https://www.themoviedb.org/assets/2/v4/glyphicons/basic/glyphicons-basic-36-user-female-grey-d9222f16ec16a33ed5e2c9bbdca07a4c48db14008bbebbabced8f8ed1fa2ad59.svg"