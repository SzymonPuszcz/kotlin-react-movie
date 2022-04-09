package movie

import csstype.AlignItems
import csstype.JustifyContent
import mui.material.AppBar
import mui.material.AppBarPosition
import mui.material.Grid
import mui.material.GridDirection
import mui.material.Link
import mui.material.LinkUnderline
import mui.material.Toolbar
import mui.system.ResponsiveStyleValue
import mui.system.sx
import react.FC
import react.Props

val AppAppBar = FC<Props> {
    AppBar {
        position = AppBarPosition.fixed
        Toolbar {
            sx {
                justifyContent = JustifyContent.spaceBetween
            }
            Grid {
                container = true
                direction = ResponsiveStyleValue(GridDirection.row)
                sx {
                    justifyContent = JustifyContent.center
                    alignItems = AlignItems.center
                }
                Grid {
                    item = true
                    Link {
                        href = "/"
                        variant = "h5"
                        underline = LinkUnderline.none
                        color = "inherit"
                        +"The Movie"
                    }
                }
            }
        }
    }
}