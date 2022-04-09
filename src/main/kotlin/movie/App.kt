package movie

import react.FC
import react.Props
import react.router.dom.HashRouter

val App = FC<Props> {
    HashRouter {
        ThemeModule {
            AppAppBar()
            Content()
        }
    }
}
