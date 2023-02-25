package com.leoleo.androidapptemplate.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.leoleo.androidapptemplate.ui.preview.PreviewDevices
import com.leoleo.androidapptemplate.ui.theme.AndroidAppTemplateTheme

@Composable
fun AppSurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    AndroidAppTemplateTheme {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing),
            color = MaterialTheme.colorScheme.background,
            content = content,
        )
    }
}

@PreviewDevices
@Composable
private fun Prev_AppSurface() {
    AppSurface {
        Text(
            text = "Hello, World!",
            modifier = Modifier.wrapContentSize()
        )
    }
}