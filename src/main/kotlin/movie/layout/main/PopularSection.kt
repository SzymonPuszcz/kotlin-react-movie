package movie.layout.main

import csstype.Display
import csstype.FlexDirection
import csstype.Overflow
import csstype.pct
import csstype.px
import movie.api.PopularDto
import mui.material.Box
import mui.material.ImageList
import mui.material.Typography
import mui.system.sx
import react.FC
import react.Props
import react.router.useNavigate

external interface PopularSectionProps : Props {
    var popularMovies: List<PopularDto>
}
val PopularSection = FC<PopularSectionProps> { props ->
    Box {
        Typography {
            variant = "h4"
            +"Popular"
        }
        Box {
            sx {
                maxHeight = 100.pct
                overflow = Overflow.scroll
            }
            ImageList {
                gap = 15
                sx {
                    display = Display.flex
                    flexDirection = FlexDirection.row
                }
                props.popularMovies.map {
                    PopularItem {
                        movie = it
                    }
                }
            }
        }
    }
}