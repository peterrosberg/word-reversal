package com.pepp.wordreversal.rest

import com.pepp.wordreversal.service.WordReversalService
import org.springframework.web.bind.annotation.*

@RestController
class Controller(
        val wordReversalService: WordReversalService
) {

    @PostMapping("/reversal")
    fun greeting(@RequestBody input: String): String {
        return wordReversalService.reverseWords(input)
    }
}