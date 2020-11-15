package com.wordsmith.wordreversal.rest

import com.wordsmith.wordreversal.model.PreviousTranslation
import com.wordsmith.wordreversal.model.ReversalInput
import com.wordsmith.wordreversal.model.ReversalResult
import com.wordsmith.wordreversal.service.MainService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/api")
class Controller(
        val mainService: MainService
) {

    @PostMapping("/reversal")
    fun wordReversal(@RequestBody input: ReversalInput): ReversalResult {
        log.info("Received request: $input")
        return mainService.reverseWords(input)
    }

    @GetMapping("/latest")
    fun wordReversal(): List<PreviousTranslation> {
        log.info("Received request for lastReversals")
        return mainService.getLastReversals()
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(Controller::class.java)
    }
}