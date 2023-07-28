package com.leoleo.androidapptemplate.ui.compact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.leoleo.androidapptemplate.R
import com.leoleo.androidapptemplate.ui.component.AppSurface
import com.leoleo.androidapptemplate.ui.preview.PreviewPhoneDevice

@Composable
fun CompactMainScreen(
    modifier: Modifier = Modifier,
) {
    var count by rememberSaveable { mutableStateOf(1) }
    modifier.testTag(stringResource(id = R.string.test_tag_compact_main_screen))
    CompactMainScreenStateless(modifier, count, onClick = { count++ })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CompactMainScreenStateless(modifier: Modifier, count: Int, onClick: () -> Unit) {
    AppSurface {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = { onClick() }) {
                    Text(text = "+")
                }
            }, content = { padding ->
                Column(
                    modifier = modifier.padding(padding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "CompactMainScreen: $count")
                }
            })
    }
}

@PreviewPhoneDevice
@Composable
private fun Prev_CompactMainScreen() {
    val modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    CompactMainScreenStateless(modifier, 10) {}
}