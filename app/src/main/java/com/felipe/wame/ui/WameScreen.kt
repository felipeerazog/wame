package com.felipe.wame.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.felipe.wame.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WameScreen(
    onSend: (countryCode: String, phoneNumber: String) -> Unit
) {
    var phoneNumber by remember { mutableStateOf(TextFieldValue()) }

    var countryCode by remember { mutableStateOf("57") }

    Scaffold(
        modifier = Modifier.padding(16.dp),
        topBar = {
            WameTopbar(
                onSend = { onSend(countryCode, phoneNumber.text) }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CountryField(countryCode) { newCountryCode ->
                countryCode = newCountryCode
            }
            PhoneNumberField(phoneNumber) { newPhoneNumber ->
                phoneNumber = newPhoneNumber
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WameTopbar(
    onSend: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(stringResource(id = R.string.app_name))
        },
        actions = {
            IconButton(onClick = onSend) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}