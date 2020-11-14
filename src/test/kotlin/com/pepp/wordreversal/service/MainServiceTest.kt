package com.pepp.wordreversal.service

import com.pepp.wordreversal.db.DatabaseService
import com.pepp.wordreversal.db.ReversalEntity
import com.pepp.wordreversal.logic.WordReversalService
import com.pepp.wordreversal.model.PreviousTranslation
import com.pepp.wordreversal.model.ReversalInput
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.ZonedDateTime

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
    fun `reversing an empty sentence does not save to database`() {

        val expectedResult = ""
        val input = ""

        every { wordReversalService.reverseWords(input) } returns expectedResult
        every { databaseService.save(any()) } returnsArgument 0

        val result = service.reverseWords(ReversalInput(input))

        assertThat(result.result).isEqualTo(expectedResult)

        verify(inverse = true) { databaseService.save(any()) }
    }

    @Test
    fun `getting the last translated sentences`() {

        val time1 = ZonedDateTime.now()
        val time2 = time1.minusHours(1L)
        every { databaseService.getLatest() } returns listOf(
                createEntity("i1", "r1", time1),
                createEntity("i2", "r2", time2)
        )

        val result = service.getLastReversals()

        assertThat(result).containsExactly(
                PreviousTranslation("i1", "r1", time1),
                PreviousTranslation("i2", "r2", time2)
        )
    }

    private fun createEntity(input: String, result: String, time: ZonedDateTime): ReversalEntity {
        val reversalEntity = ReversalEntity(input, result)
        reversalEntity.created = time
        return reversalEntity
    }
}