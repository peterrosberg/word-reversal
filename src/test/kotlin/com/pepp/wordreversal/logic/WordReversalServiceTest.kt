package com.pepp.wordreversal.logic

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

    @Test
    fun `Handling the ' in the english language`() {

        val result = service.reverseWords("It's mine")
        assertThat(result).isEqualTo("s'tI enim")
    }

    @Test
    fun `Handling words with accents`() {

        assertSingleWord("Señor")
        assertSingleWord("Café")
        assertSingleWord("soupçon")
        assertSingleWord("über")
        assertSingleWord("Zoë")
        assertSingleWord("entrepôt")
        assertSingleWord("Đinđić")
        assertSingleWord("Chișinău")
    }

    private fun assertSingleWord(input: String) {
        assertThat(service.reverseWords(input)).`as`("Reversing $input correctly").isEqualTo(input.reversed())
    }
}