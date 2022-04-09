package movie

import csstype.px
import movie.layout.main.MainPage
import movie.layout.movie.MoviePage
import mui.material.Typography
import mui.system.Box
import mui.system.sx
import react.FC
import react.Props
import react.create
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div
import react.router.Outlet
import react.router.Route
import react.router.Routes

val DEFAULT_PADDING = 30.px

val Content = FC<Props> {
    div {
        Routes {
            Route {
                path = "/"
                element = Box.create {
                    component = ReactHTML.main
                    sx {
                        paddingTop = 60.px
                    }
                    Outlet()
                }

                Route {
                    index = true
                    element = MainPage.create()
                }

                Route {
                    path = "/movie/:id"
                    element = MoviePage.create()
                }

                Route {
                    path = "*"
                    element = Typography.create { +"404 Page Not Found" }
                }

            }
        }
    }
}
