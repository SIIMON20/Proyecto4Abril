package com.example.proyecto25febrero.navigation

sealed class AppScrens (val route: String) {

    object AppCartas : AppScrens(route = "Pantalla2")
    object LoginScreen : AppScrens(route = "Pantalla_de_inicio")
}