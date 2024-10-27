package org.pupuseria

import org.pupuseria.services.OrdenServiceImpl

fun main() {
    val ordenService = OrdenServiceImpl()
    val menu = MenuUI(ordenService)
    menu.iniciar()
}