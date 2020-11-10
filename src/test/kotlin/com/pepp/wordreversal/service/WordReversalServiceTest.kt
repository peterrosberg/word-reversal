package com.pepp.wordreversal.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WordReversalServiceTest {

    private val service: WordReversalService = WordReversalService()

    @Test
    fun `Handle empty string`() {

        val result = service.reverseWords("")
        assertThat(result).isEqualTo("")
    }

    @Test
    fun `Reverses a basic word correctly`() {

        val result = service.reverseWords("Hello")
        assertThat(result).isEqualTo("olleH")
    }

    @Test
    fun `Reverses a sentence correctly`() {

        val result = service.reverseWords("Hello this is dog")
        assertThat(result).isEqualTo("olleH siht si god")
    }

    @Test
    fun `Reverses a sentence with odd characters correctly`() {

        val result = service.reverseWords("Hello, this is dog!")
        assertThat(result).isEqualTo("olleH, siht si god!")
    }

    @Test
    fun `Reverses a sentence starting with odd characters correctly`() {

        val result = service.reverseWords("¿Hello, this is dog?")
        assertThat(result).isEqualTo("¿olleH, siht si god?")
    }

    @Test
    fun `Reverses a sentence with swedish characters correctly`() {

        val result = service.reverseWords("Åke, du är på en Ö")
        assertThat(result).isEqualTo("ekÅ, ud rä åp ne Ö")
    }

    @Test
    fun `Reverses a sentence with combined words correctly`() {

        val result = service.reverseWords("Double-letter words are words")
        assertThat(result).isEqualTo("elbuoD-rettel sdrow era sdrow")
    }
}