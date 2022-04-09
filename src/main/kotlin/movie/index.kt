package movie

import kotlinx.browser.document
import react.create
import react.dom.render

fun main() {
    render(
        element = App.create(),
        container = document.createElement("div").also { document.body!!.appendChild(it) },
    )
}