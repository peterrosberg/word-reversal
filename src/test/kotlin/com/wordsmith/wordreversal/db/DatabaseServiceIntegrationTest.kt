package com.wordsmith.wordreversal.db

import com.wordsmith.wordreversal.model.entity.ReversalEntity
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

@SpringBootTest
@ActiveProfiles("test")
class DatabaseServiceIntegrationTest {

    @Autowired
    lateinit var databaseService: DatabaseService

    @Test
    fun `Created time and id is set correctly`() {

        val now = ZonedDateTime.now()
        val entity = ReversalEntity(
                input = "YO!",
                result = "OY!"
        )

        databaseService.save(entity)

        val result = databaseService.getLatest()

        assertThat(result.size).isEqualTo(1)
        assertThat(result.first().id).isNotEqualTo(0)
        assertThat(result.first().created).isCloseTo(now, Assertions.within(1, ChronoUnit.SECONDS))
    }

    @Test
    fun `Get the five last sentences`() {

        val tooOldEntity = ReversalEntity(
                input = "Too Old!",
                result = "Too Old!"
        )

        databaseService.save(tooOldEntity)

        val entities = (1..5).map { ReversalEntity(it.toString(), it.toString()) }
                .map { databaseService.save(it) }

        val result = databaseService.getLatest()

        assertThat(result.size).isEqualTo(5)
        assertThat(result).containsExactlyInAnyOrderElementsOf(entities)
    }
}