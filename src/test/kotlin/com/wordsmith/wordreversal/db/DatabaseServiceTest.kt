package com.wordsmith.wordreversal.db

import com.wordsmith.wordreversal.model.entity.ReversalEntity
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class DatabaseServiceTest {

    @MockK
    lateinit var repository: ReversalRepository

    @InjectMockKs
    lateinit var databaseService: DatabaseService

    @Test
    fun `Cache last sentences`() {

        val entity = ReversalEntity(
                input = "YO!",
                result = "OY!"
        )

        every { repository.save<ReversalEntity>(any()) } returnsArgument 0
        every { repository.findTop5ByOrderByCreatedDesc() } returns listOf(entity)


        databaseService.save(entity)
        assertThat(databaseService.getLatest()).`as`("Read 1: uncached").containsExactly(entity)
        assertThat(databaseService.getLatest()).`as`("Read 2: cached").containsExactly(entity)

        databaseService.save(entity)
        assertThat(databaseService.getLatest()).`as`("Read 3: uncached").containsExactly(entity)
        assertThat(databaseService.getLatest()).`as`("Read 4: cached").containsExactly(entity)

        verify(exactly = 2) { repository.findTop5ByOrderByCreatedDesc() }
    }

    @Test
    fun `Only cache successful fetch`() {

        val entity = ReversalEntity(
                input = "YO!",
                result = "OY!"
        )

        every { repository.save<ReversalEntity>(any()) } returnsArgument 0
        every { repository.findTop5ByOrderByCreatedDesc() } throws RuntimeException("TEST") andThen listOf(entity)

        try {
            databaseService.getLatest()
        } catch (e: RuntimeException) {

        }

        val result = databaseService.getLatest()

        assertThat(result).containsExactly(entity)
    }
}