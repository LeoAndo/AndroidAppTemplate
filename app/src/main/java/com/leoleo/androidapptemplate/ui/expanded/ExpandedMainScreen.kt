package com.leoleo.androidapptemplate.ui.expanded

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.leoleo.androidapptemplate.R
import com.leoleo.androidapptemplate.ui.component.AppSurface
import com.leoleo.androidapptemplate.ui.preview.PreviewFoldableDevice

@Composable
fun ExpandedMainScreen(
    modifier: Modifier = Modifier,
) {
    var count by rememberSaveable { mutableStateOf(1) }
    ExpandedMainScreenStateless(modifier, count, onClick = { count++ })
}

@Composable
private fun ExpandedMainScreenStateless(
    modifier: Modifier = Modifier,
    count: Int,
    onClick: () -> Unit
) {
    AppSurface(modifier) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = { onClick() }) {
                    Text(text = "+")
                }
            }, content = { padding ->
                val pv = PaddingValues(
                    start = padding.calculateLeftPadding(LayoutDirection.Ltr) + 16.dp,
                    top = padding.calculateTopPadding() + 16.dp,
                    bottom = padding.calculateBottomPadding() + 16.dp,
                    end = padding.calculateRightPadding(LayoutDirection.Ltr) + 16.dp,
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(pv)
                        .testTag(stringResource(id = R.string.test_tag_expanded_main_screen)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "ExpandedMainScreenStateless: $count")
                }
            })
    }
}

@PreviewFoldableDevice
@Composable
private fun Prev_ExpandedMainScreen() {
    ExpandedMainScreenStateless(count = 10) {}
}