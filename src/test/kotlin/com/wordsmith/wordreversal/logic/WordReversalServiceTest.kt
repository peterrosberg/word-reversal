package com.wordsmith.wordreversal.logic

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class WordReversalServiceTest {

    @MockK
    lateinit var properties: ReversalProperties

    lateinit var service: WordReversalService

    @BeforeEach
    fun setUpProperties() {
        every { properties.delimitingCharacters } returns ".,"

        service = WordReversalService(properties)
    }

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
    fun `Reverses a sentence with delimiting characters correctly`() {

        val result = service.reverseWords("Hello, this is dog.")
        assertThat(result).isEqualTo("olleH, siht si god.")
    }

    @Test
    fun `Reverses a sentence with starting with delimiting characters correctly`() {

        val result = service.reverseWords(".Hello, this is dog")
        assertThat(result).isEqualTo(".olleH, siht si god")
    }

    @Test
    fun `Ignore trailing whitespace`() {

        val result = service.reverseWords("    Hello, this is dog   \t  ")
        assertThat(result).isEqualTo("olleH, siht si god")
    }

    @Test
    fun `All whitespace are delimiters`() {

        val result = service.reverseWords("Hello\tthis\ris\ndog")
        assertThat(result).isEqualTo("olleH\tsiht\rsi\ngod")
    }

    @Test
    fun `Reverses a sentence with combined words correctly`() {

        val result = service.reverseWords("It's a double-trouble")
        assertThat(result).isEqualTo("s'tI a elbuort-elbuod")
    }

    @Test
    fun `Odd characters and numbers are reversed`() {

        assertSingleWord("089-47/'&%!")
    }

    @Test
    fun `Handling words with accents`() {

        assertSingleWord("Åke")
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