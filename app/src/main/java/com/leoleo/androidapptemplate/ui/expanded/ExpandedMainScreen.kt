package com.leoleo.androidapptemplate.ui.expanded

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.leoleo.androidapptemplate.R
import com.leoleo.androidapptemplate.ui.component.AppSurface
import com.leoleo.androidapptemplate.ui.preview.PreviewFoldableDevice

@Composable
fun ExpandedMainScreen() {
    var count by rememberSaveable { mutableStateOf(1) }
    val modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .testTag(stringResource(id = R.string.test_tag_expanded_main_screen))
    ExpandedMainScreenStateless(modifier, count, onClick = { count++ })
}

@Composable
private fun ExpandedMainScreenStateless(modifier: Modifier, count: Int, onClick: () -> Unit) {
    Column(modifier = modifier) {
        Button(onClick = { onClick() }) {
            Text(text = "ExpandedMainScreen: $count")
        }
    }
}

@PreviewFoldableDevice
@Composable
private fun Prev_ExpandedMainScreen() {
    val modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    AppSurface {
        ExpandedMainScreenStateless(modifier, 10) {}
    }
}