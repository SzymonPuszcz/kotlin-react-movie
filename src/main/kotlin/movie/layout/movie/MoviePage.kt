package movie.layout.movie

import csstype.AlignItems
import csstype.BackgroundPosition
import csstype.BackgroundSize
import csstype.Display
import csstype.FlexDirection
import csstype.JustifyContent
import csstype.Overflow
import csstype.deg
import csstype.linearGradient
import csstype.pct
import csstype.px
import csstype.rgba
import csstype.stop
import csstype.url
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import movie.DEFAULT_PADDING
import movie.Global
import movie.api.CastDto
import movie.api.CreditsDto
import movie.api.MovieDto
import movie.toBackdropUrl
import movie.toPosterUrl
import movie.toProfileUrl
import mui.material.Box
import mui.material.Grid
import mui.material.ImageList
import mui.material.ImageListItem
import mui.material.ImageListItemBar
import mui.material.Stack
import mui.material.Typography
import mui.system.ResponsiveStyleValue
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.img
import react.router.useParams
import react.useEffectOnce
import react.useState

private val mainScope = MainScope()

val MoviePage = FC<Props> {
    val params = useParams()
    var movie: MovieDto? by useState(null)
    var casts: List<CastDto> by useState(emptyList())

    useEffectOnce {
        mainScope.launch {
            params["id"]?.let {
                movie = fetchMovie(it)
                casts = fetchCasts(it)
            }
        }
    }

    Box {
        sx {
            padding = 0.px
            backgroundImage =
                url(movie?.backdropPath.toBackdropUrl())
            backgroundPosition = BackgroundPosition.center
            backgroundSize = BackgroundSize.cover
            minHeight = 500.px
            maxHeight = 550.px
        }
        Box {
            sx {
                padding = 0.px
                display = Display.flex
                alignItems = AlignItems.center
                backgroundSize = BackgroundSize.cover
                backgroundPosition = BackgroundPosition.center
                minHeight = 500.px
                maxHeight = 550.px
                backgroundImage =
                    linearGradient(90.deg,
                        stop(rgba(10, 31, 52, 1.0), 0.pct),
                        stop(rgba(10, 31, 52, 0.84), 100.pct))
            }
            Grid {
                container = true
                spacing = ResponsiveStyleValue(4)
                Grid {
                    item = true
                    xs = 4
                    sx {
                        display = Display.flex
                        alignItems = AlignItems.end
                        justifyContent = JustifyContent.end
                    }
                    img {
                        src = movie?.posterPath.toPosterUrl()
                    }
                }
                Grid {
                    item = true
                    md = 6
                    Stack {
                        spacing = ResponsiveStyleValue(10)
                        Stack {
                            Typography {
                                component = ReactHTML.div
                                variant = "h2"
                                +movie?.title.orEmpty()
                            }
                            Typography {
                                variant = "h6"
                                +"${movie?.genres?.joinToString { it.name }}, ${movie?.runtime} min"
                            }
                        }
                        Stack {
                            Typography {
                                variant = "h4"
                                +"Description"
                            }
                            Typography {
                                variant = "body1"
                                +movie?.overview.orEmpty()
                            }
                        }
                    }
                }
            }
        }
    }
    Box {
        sx {
            padding = DEFAULT_PADDING
        }
        Box {
            Typography {
                variant = "h4"
                +"Cast"
            }
            Box {
                sx {
                    maxHeight = 100.pct
                    overflow = Overflow.scroll
                }
                ImageList {
                    cols = 10
                    sx {
                        display = Display.flex
                        flexDirection = FlexDirection.row
                        padding = 0.px
                    }
                    casts.map { cast ->
                        ImageListItem {
                            sx {
                                minWidth = 200.px
                            }
                            img {
                                src = cast.profilePath.toProfileUrl()
                            }
                            ImageListItemBar {
                                title = ReactNode(cast.name)
                                subtitle = ReactNode("as ${cast.character}")
                            }
                        }
                    }
                }
            }
        }
    }
}

suspend fun fetchMovie(id: String): MovieDto =
    window
        .fetch("${Global.BASE_URL}/movie/$id?api_key=${Global.API_KEY}")
        .await()
        .text()
        .await()
        .let {
            Global.Json.decodeFromString(it)
        }

suspend fun fetchCasts(id: String): List<CastDto> =
    window
        .fetch("${Global.BASE_URL}/movie/$id/credits?api_key=${Global.API_KEY}")
        .await()
        .text()
        .await()
        .let {
            Global.Json.decodeFromString<CreditsDto>(it).cast
        }

