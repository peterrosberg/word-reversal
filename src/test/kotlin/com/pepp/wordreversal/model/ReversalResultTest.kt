package com.pepp.wordreversal.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester

@JsonTest
class ReversalResultTest {

    @Autowired
    private lateinit var json: JacksonTester<ReversalResult>

    @Test
    fun `deserialisation test`() {

        val result = ReversalResult(
                result = "Yes Sentence"
        )

        val expectedJson = """
            {
                "result": "Yes Sentence"
            }
        """.trimIndent()

        Assertions.assertThat(json.write(result)).isEqualToJson(expectedJson)

    }

}