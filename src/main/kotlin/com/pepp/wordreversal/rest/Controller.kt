package com.pepp.wordreversal.rest

import com.pepp.wordreversal.model.ReversalInput
import com.pepp.wordreversal.model.ReversalResult
import com.pepp.wordreversal.service.MainService
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/api")
class Controller(
        val mainService: MainService
) {

    @PostMapping("/reversal")
    fun wordReversal(@RequestBody input: ReversalInput): ReversalResult {
        println("Caught one!")
        return mainService.reverseWords(input)
    }

    @GetMapping("/latest")
    fun wordReversal(): List<String> {
        return mainService.getLastReversals()
    }
}