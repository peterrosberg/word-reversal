package com.pepp.wordreversal.rest

import com.pepp.wordreversal.service.WordReversalService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
        val wordReversalService: WordReversalService
) {

    @PostMapping("/reversal")
    fun wordReversal(@RequestBody input: String): String {
        return wordReversalService.reverseWords(input)
    }
}