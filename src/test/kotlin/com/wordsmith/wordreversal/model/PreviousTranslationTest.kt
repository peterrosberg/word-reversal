package com.wordsmith.wordreversal.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester
import java.time.ZonedDateTime

@JsonTest
class PreviousTranslationTest {

    @Autowired
    private lateinit var json: JacksonTester<PreviousTranslation>

    @Test
    fun `Serialisation test`() {

        val result = PreviousTranslation(
                result = "Yes Sentence",
                sentence = "Sentence",
                time = ZonedDateTime.parse("2020-01-23T08:25:43.511452Z")
        )

        val expectedJson = """
            {
                "result": "Yes Sentence",
                "sentence": "Sentence",
                "time": "2020-01-23T08:25:43.511452Z"
            }
        """.trimIndent()

        Assertions.assertThat(json.write(result)).isEqualToJson(expectedJson)
    }
}