package com.leoleo.androidapptemplate.ui.medium

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.leoleo.androidapptemplate.R
import com.leoleo.androidapptemplate.ui.component.AppSurface
import com.leoleo.androidapptemplate.ui.preview.PreviewFoldableDevice

@Composable
fun MediumMainScreen() {
    var count by rememberSaveable { mutableStateOf(1) }
    val modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .testTag(stringResource(id = R.string.test_tag_medium_main_screen))
    MediumMainScreenStateless(modifier, count, onClick = { count++ })
}

@Composable
private fun MediumMainScreenStateless(modifier: Modifier, count: Int, onClick: () -> Unit) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { onClick() }) {
            Text(text = "MediumMainScreen: $count")
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
        MediumMainScreenStateless(modifier, 10) {}
    }
}