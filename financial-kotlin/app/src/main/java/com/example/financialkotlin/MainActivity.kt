package com.example.financialkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financialkotlin.presentation.screens.CalendarScreen
import com.example.financialkotlin.presentation.screens.Navigation
import com.example.financialkotlin.presentation.util.Route
import com.example.financialkotlin.ui.theme.FinancialKotlinTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val navController = rememberNavController()

      FinancialKotlinTheme {
        NavHost(navController = navController, startDestination = Route.HomeScreen.route) {
          composable(route = Route.HomeScreen.route) {
            Navigation()
          }
        }
      }
    }
  }
}
