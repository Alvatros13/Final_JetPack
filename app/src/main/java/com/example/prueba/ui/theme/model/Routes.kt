package com.example.prueba.ui.theme.model

sealed class Routes(val route: String) {
    object Pantalla01 : Routes("pantalla1")
    object Pantalla02 : Routes("pantalla2")
    object Pantalla03 : Routes("pantalla3")

}