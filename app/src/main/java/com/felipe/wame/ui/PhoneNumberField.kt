package com.felipe.wame.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.felipe.wame.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNumberField(
    phoneNumber: TextFieldValue,
    onPhoneNumberChange: (newValue: TextFieldValue) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(text = stringResource(R.string.phone_number))
        },
        value = phoneNumber,
        onValueChange = {
            onPhoneNumberChange(it)
        },
        leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = null) },
        trailingIcon = {
            WameTrailingIcon(phoneNumber.text.isNotEmpty()) {
                onPhoneNumberChange(TextFieldValue())
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = PhoneNumberTransformation()
    )
}

@Composable
fun WameTrailingIcon(
    phoneNumberIsNotEmpty: Boolean,
    onClick: () -> Unit
) {
    if (phoneNumberIsNotEmpty) {
        Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = null,
            modifier = Modifier.clickable { onClick() }
        )
    }
}
