package com.pepp.wordreversal

import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
class WordReversalIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun reversesBasicString() {
        mockMvc.perform(post("/reversal").content("ola salo"))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("alo olas")))
    }
}
