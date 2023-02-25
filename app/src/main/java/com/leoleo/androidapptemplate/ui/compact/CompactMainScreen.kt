package com.leoleo.androidapptemplate.ui.compact

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.leoleo.androidapptemplate.R
import com.leoleo.androidapptemplate.ui.component.AppSurface
import com.leoleo.androidapptemplate.ui.preview.PreviewFoldableDevice

@Composable
fun CompactMainScreen() {
    var count by rememberSaveable { mutableStateOf(1) }
    val modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .testTag(stringResource(id = R.string.test_tag_compact_main_screen))
    CompactMainScreenStateless(modifier, count, onClick = { count++ })
}

@Composable
private fun CompactMainScreenStateless(modifier: Modifier, count: Int, onClick: () -> Unit) {
    Column(modifier = modifier) {
        Button(onClick = { onClick() }) {
            Text(text = "CompactMainScreen: $count")
        }
    }
}

@PreviewFoldableDevice
@Composable
private fun Prev_CompactMainScreen() {
    val modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    AppSurface {
        CompactMainScreenStateless(modifier, 10) {}
    }
}