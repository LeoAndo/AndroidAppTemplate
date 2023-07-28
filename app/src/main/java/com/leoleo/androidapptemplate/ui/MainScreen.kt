package com.leoleo.androidapptemplate.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    MainScreen(windowWidthSizeClass = WindowWidthSizeClass.Compact)
}

@PreviewPhoneDevice
@Composable
private fun Prev_MainScreen_Medium() {
    MainScreen(windowWidthSizeClass = WindowWidthSizeClass.Medium)
}

@PreviewPhoneDevice
@Composable
private fun Prev_MainScreen_Expanded() {
    MainScreen(windowWidthSizeClass = WindowWidthSizeClass.Expanded)
}