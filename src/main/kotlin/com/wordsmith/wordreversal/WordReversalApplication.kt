package com.wordsmith.wordreversal

import com.wordsmith.wordreversal.logic.ReversalProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(ReversalProperties::class)
class WordReversalApplication

fun main(args: Array<String>) {
    runApplication<WordReversalApplication>(*args)
}
