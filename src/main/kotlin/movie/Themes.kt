package movie

import kotlinx.js.jso
import mui.material.styles.createTheme
import mui.material.PaletteMode

object Themes {
    val Dark = createTheme(
        jso {
            palette = jso { mode = PaletteMode.dark }
        }
    )
}