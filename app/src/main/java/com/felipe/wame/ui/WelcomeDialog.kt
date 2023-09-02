package com.felipe.wame.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.felipe.wame.R

@Composable
fun WelcomeDialog(
    openAlertDialog: Boolean,
    onDismissRequest: () -> Unit
) {
    if (openAlertDialog) {
        AlertDialog(
            title = {
                Text(text = stringResource(id = R.string.hello) + " \uD83D\uDC4B")
            },
            text = {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(stringResource(id = R.string.app_name))
                        }
                        append(" " + stringResource(R.string.welcome_description))
                    }
                )
            },
            onDismissRequest = {
                onDismissRequest()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text(stringResource(R.string.lets_go))
                }
            }
        )
    }
}

@Preview(locale = "es")
@Composable
fun WelcomeDialogEsPreview() {
    WelcomeDialog(
        openAlertDialog = true,
        onDismissRequest = {}
    )
}

@Preview(locale = "en")
@Composable
fun WelcomeDialogPreview() {
    WelcomeDialog(
        openAlertDialog = true,
        onDismissRequest = {}
    )
}
