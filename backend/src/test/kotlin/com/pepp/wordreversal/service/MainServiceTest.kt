package com.pepp.wordreversal.service

import com.pepp.wordreversal.db.DatabaseService
import com.pepp.wordreversal.db.ReversalEntity
import com.pepp.wordreversal.logic.WordReversalService
import com.pepp.wordreversal.model.ReversalInput
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class MainServiceTest {

    @MockK
    lateinit var databaseService: DatabaseService

    @MockK
    lateinit var wordReversalService: WordReversalService

    @InjectMockKs
    lateinit var service: MainService

    @Test
    fun `reversing a sentence saves to database`() {

        val expectedResult = "reversed"
        val input = "sentence"

        every { wordReversalService.reverseWords(input) } returns expectedResult
        every { databaseService.save(any()) } returnsArgument 0

        val result = service.reverseWords(ReversalInput(input))

        assertThat(result.result).isEqualTo(expectedResult)

        verify { databaseService.save(ReversalEntity(input, expectedResult)) }
    }

    @Test
    fun `getting the last translated sentences`() {

        every { databaseService.getLatest() } returns listOf(
                ReversalEntity("", "1"),
                ReversalEntity("", "2"),
                ReversalEntity("", "3")
        )

        val result = service.getLastReversals()

        assertThat(result).containsExactly("1", "2", "3")
    }

}