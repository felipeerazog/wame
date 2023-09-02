package com.felipe.wame

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.felipe.wame.ui.WameScreen
import com.felipe.wame.ui.WelcomeDialog
import com.felipe.wame.ui.theme.WameTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiState = viewModel.uiState.collectAsState().value

            WameTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WameScreen { countryCode, phoneNumber ->
                        viewModel.sendWhatsApp(countryCode, phoneNumber)
                    }
                    WelcomeDialog(
                        openAlertDialog = uiState.showDialog,
                        onDismissRequest = { viewModel.hideDialog() },
                    )
                }
            }
            val uriValue = uiState.uriValue
            if (uriValue != null) {
                openWhatsAppIntent(uriValue)
            }
        }
    }

    private fun openWhatsAppIntent(uriValue: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uriValue))
        startActivity(intent)
        viewModel.clearState()
    }
}

@Preview(locale = "es")
@Composable
fun MainActivityEsPreview() {
    WameTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            WameScreen { countryCode, phoneNumber ->

            }

        }
    }
}

@Preview(locale = "en")
@Composable
fun MainActivityPreview() {
    WameTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            WameScreen { countryCode, phoneNumber ->

            }

        }
    }
}
