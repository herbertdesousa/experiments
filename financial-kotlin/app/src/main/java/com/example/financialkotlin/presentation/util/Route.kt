package com.example.financialkotlin.presentation.util

sealed class Route(val route: String) {
  object HomeScreen: Route("home")
}