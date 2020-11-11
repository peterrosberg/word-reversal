package com.pepp.wordreversal.rest

import com.pepp.wordreversal.model.ReversalInput
import com.pepp.wordreversal.model.ReversalResult
import com.pepp.wordreversal.logic.WordReversalService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController()
@RequestMapping("/api")
class Controller(
        val wordReversalService: WordReversalService
) {

    @PostMapping("/reversal")
    fun wordReversal(@RequestBody input: ReversalInput): ReversalResult {
        println("Caught one!")
        val result = wordReversalService.reverseWords(input.sentence)
        return ReversalResult(result)
    }
}