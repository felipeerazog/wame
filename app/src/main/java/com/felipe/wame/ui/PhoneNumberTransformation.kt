package com.felipe.wame.ui

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneNumberTransformation: VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val formatted = formatPhoneNumber(text.toString())
        return TransformedText(
            AnnotatedString(formatted),
            object :OffsetMapping{
                override fun originalToTransformed(offset: Int) =  when{
                    offset <= 3 -> {
                        offset
                    }

                    offset in 4..6 -> {
                        offset+1
                    }

                    else -> {
                       offset+2
                    }
                }

                override fun transformedToOriginal(offset: Int)=when{
                    offset <= 3 -> {
                        offset
                    }

                    offset in 4..6 -> {
                        offset-1
                    }

                    else -> {
                        offset-2
                    }
                }
            }
        )
    }
}

fun formatPhoneNumber(phoneNumber: String): String {
    val phoneNumberWithoutSpaces = phoneNumber.replace(" ", "")
    val length = phoneNumberWithoutSpaces.length
    return when {
        length <= 3 -> {
            phoneNumberWithoutSpaces
        }

        length in 4..6 -> {
            val firstGroup = phoneNumberWithoutSpaces.substring(0, 3)
            val secondGroup = phoneNumberWithoutSpaces.substring(3, length)
            "$firstGroup $secondGroup"
        }

        else -> {
            val firstGroup = phoneNumberWithoutSpaces.substring(0, 3)
            val secondGroup = phoneNumberWithoutSpaces.substring(3, 6)
            val thirdGroup = phoneNumberWithoutSpaces.substring(6, length)
            "$firstGroup $secondGroup $thirdGroup"
        }
    }
}
