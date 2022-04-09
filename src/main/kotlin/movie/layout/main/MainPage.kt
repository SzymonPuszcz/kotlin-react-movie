package movie.layout.main

import csstype.AlignItems
import csstype.BackgroundPosition
import csstype.BackgroundSize
import csstype.Display
import csstype.JustifyContent
import csstype.px
import csstype.url
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import movie.DEFAULT_PADDING
import movie.Global.API_KEY
import movie.Global.BASE_URL
import movie.Global.Json
import movie.api.PagedResponse
import movie.api.PopularDto
import mui.material.Box
import mui.material.Grid
import mui.material.GridDirection
import mui.material.Typography
import mui.system.ResponsiveStyleValue
import mui.system.sx
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.useEffectOnce
import react.useState

private val mainScope = MainScope()

val MIN_BANNER_HEIGHT = 500.px

val MainPage = FC<Props> {
    var populars: List<PopularDto> by useState(emptyList())
    useEffectOnce {
        mainScope.launch {
            populars = fetchPopular()
        }
    }

    div {
        Box {
            sx {
                padding = 0.px
                backgroundImage =
                    url("https://www.themoviedb.org/t/p/w1920_and_h600_multi_faces_filter%28duotone%2C032541%2C01b4e4%29/34OGjFEbHj0E3lE2w0iTUVq0CBz.jpg")
                backgroundPosition = BackgroundPosition.center
                backgroundSize = BackgroundSize.cover
                minHeight = 500.px
                maxHeight = 550.px
                display = Display.flex
                justifyContent = JustifyContent.center
            }
            Grid {
                container = true
                direction = ResponsiveStyleValue(GridDirection.column)
                sx {
                    padding = 100.px
                    justifyContent = JustifyContent.center
                    alignItems = AlignItems.flexStart
                }
                Grid {
                    item = true
                    Typography {
                        variant = "h3"
                        +"Welcome."
                    }
                    Typography {
                        variant = "h4"
                        +"Millions of movies, TV shows and people to discover. Explore now."
                    }

                }
            }

        }
        Box {
            sx {
                padding = DEFAULT_PADDING
            }
            PopularSection {
                popularMovies = populars
            }
        }
    }
}

suspend fun fetchPopular(): List<PopularDto> =
    window
        .fetch("$BASE_URL/movie/popular?api_key=$API_KEY")
        .await()
        .text()
        .await()
        .let {
            Json.decodeFromString<PagedResponse<PopularDto>>(it).results
        }
