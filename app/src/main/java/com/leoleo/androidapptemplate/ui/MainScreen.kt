package com.leoleo.androidapptemplate.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leoleo.androidapptemplate.ui.compact.CompactMainScreen
import com.leoleo.androidapptemplate.ui.expanded.ExpandedMainScreen
import com.leoleo.androidapptemplate.ui.medium.MediumMainScreen
import com.leoleo.androidapptemplate.ui.preview.PreviewPhoneDevice

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    windowWidthSizeClass: WindowWidthSizeClass
) {
    when (windowWidthSizeClass) {
        WindowWidthSizeClass.Compact -> CompactMainScreen(modifier)
        WindowWidthSizeClass.Medium -> MediumMainScreen(modifier)
        WindowWidthSizeClass.Expanded -> ExpandedMainScreen(modifier)
    }
}

@PreviewPhoneDevice
@Composable
private fun Prev_MainScreen_Compact() {
    val modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    MainScreen(modifier = modifier, windowWidthSizeClass = WindowWidthSizeClass.Compact)
}

@PreviewPhoneDevice
@Composable
private fun Prev_MainScreen_Medium() {
    val modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    MainScreen(modifier = modifier, windowWidthSizeClass = WindowWidthSizeClass.Medium)
}

@PreviewPhoneDevice
@Composable
private fun Prev_MainScreen_Expanded() {
    val modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    MainScreen(modifier = modifier, windowWidthSizeClass = WindowWidthSizeClass.Expanded)
}