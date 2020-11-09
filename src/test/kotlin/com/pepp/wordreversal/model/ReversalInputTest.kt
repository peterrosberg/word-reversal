package com.pepp.wordreversal.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester

@JsonTest
class ReversalInputTest {

    @Autowired
    private lateinit var json: JacksonTester<ReversalInput>

    @Test
    fun `deserialisation test`() {

        val jsonInput = """
            {
                "sentence": "Yes Sentence"
            }
        """.trimIndent()

        val expected = ReversalInput(
                sentence = "Yes Sentence"
        )

        assertThat(json.parseObject(jsonInput)).isEqualTo(expected)

    }

}