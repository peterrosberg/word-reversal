package com.pepp.wordreversal

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.hamcrest.Matchers.containsString
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post


@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class WordReversalIntegrationTest {

	@Autowired
	private lateinit var mockMvc: MockMvc

	@Test
	fun reversesBasicString() {
		mockMvc.perform(post("/reversal").content("peter"))
				.andDo(print())
				.andExpect(status().isOk)
				.andExpect(content().string(containsString("retep")))
	}


}
