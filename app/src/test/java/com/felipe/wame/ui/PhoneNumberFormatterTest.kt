package com.felipe.wame.ui

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import kotlin.random.Random


class PhoneNumberFormatterTest {

    @Test
    fun `Format 1`() {
        val result = formatPhoneNumber("1")
        assertThat(result).isEqualTo("1")
    }

    @Test
    fun `Format 12`() {
        val result = formatPhoneNumber("12")
        assertThat(result).isEqualTo("12")
    }

    @Test
    fun `Format 123`() {
        val result = formatPhoneNumber("123")
        assertThat(result).isEqualTo("123")
    }

    @Test
    fun `Format 1234`() {
        val result = formatPhoneNumber("1234")
        assertThat(result).isEqualTo("123 4")
    }

    @Test
    fun `Format 12345`() {
        val result = formatPhoneNumber("12345")
        assertThat(result).isEqualTo("123 45")
    }

    @Test
    fun `Format 123456`() {
        val result = formatPhoneNumber("123456")
        assertThat(result).isEqualTo("123 456")
    }

    @Test
    fun `Format 1234567`() {
        val result = formatPhoneNumber("1234567")
        assertThat(result).isEqualTo("123 456 7")
    }

    @Test
    fun `Format 12345678`() {
        val result = formatPhoneNumber("12345678")
        assertThat(result).isEqualTo("123 456 78")
    }

    @Test
    fun `Format 123456789`() {
        val result = formatPhoneNumber("123456789")
        assertThat(result).isEqualTo("123 456 789")
    }

    @Test
    fun `Format 1234567890`() {
        val result = formatPhoneNumber("1234567890")
        assertThat(result).isEqualTo("123 456 7890")
    }

    @Test
    fun `Format number with length greater than 10`() {
        val phoneNumber = "1234567890" +
                Random.nextInt(from = 1, until = 999).toString()
        val result = formatPhoneNumber(phoneNumber).split(" ")
        assertThat(result.size).isEqualTo(3)
        assertThat(result[0]).isEqualTo("123")
        assertThat(result[1]).isEqualTo("456")
    }
}