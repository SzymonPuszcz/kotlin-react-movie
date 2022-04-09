package movie.layout.main

import csstype.Display
import csstype.FlexDirection
import csstype.px
import emotion.css.css
import emotion.sheet.StyleSheet
import movie.api.PopularDto
import movie.toPosterUrl
import mui.material.ImageListItem
import mui.material.ImageListItemBar
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.dom.html.ReactHTML.img
import react.key
import react.router.useNavigate

//When the compiler sees such a declaration, it assumes that the implementation for the corresponding class, function or property is provided externally (by the developer or via an npm dependency), and therefore does not try to generate any JavaScript code from the declaration.
external interface PopularItemProps : Props {
    var movie: PopularDto
}
val PopularItem = FC<PopularItemProps> { props ->
    val navigate = useNavigate()

    ImageListItem {
        key = props.movie.id.toString()
        sx {
            flexDirection = FlexDirection.row
            display = Display.flex
            minWidth = 150.px
        }
        onClick = { navigate("/movie/${props.movie.id}") }
        img { src = props.movie.posterPath.toPosterUrl() }
        ImageListItemBar { title = ReactNode(props.movie.title) }
    }
}