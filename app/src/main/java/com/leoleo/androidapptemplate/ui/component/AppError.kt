package com.leoleo.androidapptemplate.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.leoleo.androidapptemplate.R
import com.leoleo.androidapptemplate.ui.preview.PreviewDevices

@Composable
fun AppError(
    modifier: Modifier = Modifier,
    message: String,
    onReload: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(text = message, color = MaterialTheme.colorScheme.error)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onReload) {
            Text(text = stringResource(R.string.reload))
        }
    }
}

@PreviewDevices
@Composable
private fun Prev_AppError() {
    AppSurface {
        AppError(
            message = "Could not load.",
            onReload = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}