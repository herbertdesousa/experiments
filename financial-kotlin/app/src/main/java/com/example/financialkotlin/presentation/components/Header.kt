package com.example.financialkotlin.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header(tabs: List<String>, onTabChange: (selectedIndex: Int) -> Unit) {
  val interactionSource = remember { MutableInteractionSource() }

  var selectedTabIndex by remember { mutableIntStateOf(0) }

  val fontSizeMap = remember { mutableStateMapOf<Int, Float>() }
  tabs.forEachIndexed { index, _ -> fontSizeMap[index] = if (index == selectedTabIndex) 32f else 16f }

  Row(
    modifier = Modifier.fillMaxWidth().padding(16.dp).height(56.dp),
    verticalAlignment = Alignment.Bottom
  ) {
    tabs.forEachIndexed { index, item ->
      val isSelected = selectedTabIndex == index
      val targetFontSize = if (isSelected) 32f else 16f

      val fontSize = fontSizeMap[index] ?: 16f
      val fontSizeState = remember { Animatable(fontSize) }

      LaunchedEffect(isSelected) {
        fontSizeState.animateTo(targetFontSize)
        fontSizeMap[index] = targetFontSize
      }

      Box(
        modifier = Modifier
          .wrapContentSize()
          .clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = {
              selectedTabIndex = index
              onTabChange(index)
            }
          )
          .padding(horizontal = 16.dp, vertical = 8.dp),
      ) {
        Text(
          text = item,
          fontSize = fontSizeState.value.sp,
          fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
        )
      }
    }
  }
}