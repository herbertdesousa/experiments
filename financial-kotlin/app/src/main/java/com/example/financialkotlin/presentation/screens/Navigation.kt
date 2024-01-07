package com.example.financialkotlin.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.financialkotlin.presentation.components.Header

@Composable
fun Navigation() {
  var selectedTabIndex by remember { mutableIntStateOf(0) }

  Column {
    Header(
      tabs = listOf("Agenda", "Gastos", "Ganhos"),
      onTabChange = { selectedTabIndex = it }
    )

    when (selectedTabIndex) {
      0 -> CalendarScreen()
    }
  }
}