package com.leoleo.androidapptemplate.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import com.leoleo.androidapptemplate.ui.compact.CompactMainScreen
import com.leoleo.androidapptemplate.ui.component.AppSurface
import com.leoleo.androidapptemplate.ui.expanded.ExpandedMainScreen
import com.leoleo.androidapptemplate.ui.medium.MediumMainScreen

@Composable
fun MainScreen(windowWidthSizeClass: WindowWidthSizeClass) {
    AppSurface {
        when (windowWidthSizeClass) {
            WindowWidthSizeClass.Compact -> CompactMainScreen()
            WindowWidthSizeClass.Medium -> MediumMainScreen()
            WindowWidthSizeClass.Expanded -> ExpandedMainScreen()
        }
    }
}