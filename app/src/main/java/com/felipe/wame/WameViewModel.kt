package com.felipe.wame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UiState(
    val uriValue: String? = null,
    val showDialog: Boolean = false
)

@HiltViewModel
class WameViewModel @Inject constructor(
    private val localStorage: LocalStorage
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val showDialog = localStorage.getShowDialogPreference()
            _uiState.update { state ->
                state.copy(showDialog = showDialog)
            }
        }
    }

    fun sendWhatsApp(countryCode: String, phoneNumber: String) {
        viewModelScope.launch {
            val uriValue = "https://wa.me/$countryCode$phoneNumber"
            _uiState.update { state ->
                state.copy(
                    uriValue = uriValue
                )
            }
        }
    }

    fun clearState() {
        viewModelScope.launch {
            _uiState.update {
                UiState()
            }
        }
    }

    fun hideDialog() {
        viewModelScope.launch {
            localStorage.setShowDialogPreference(false)
            _uiState.update { state ->
                state.copy(showDialog = false)
            }
        }
    }
}
