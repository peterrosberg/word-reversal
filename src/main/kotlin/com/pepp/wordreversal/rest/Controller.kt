package com.pepp.wordreversal.rest

import com.pepp.wordreversal.model.PreviousTranslation
import com.pepp.wordreversal.model.ReversalInput
import com.pepp.wordreversal.model.ReversalResult
import com.pepp.wordreversal.service.MainService
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