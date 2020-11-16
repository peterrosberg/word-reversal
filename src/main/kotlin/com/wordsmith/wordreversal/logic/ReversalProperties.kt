package com.wordsmith.wordreversal.logic

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "reversal")
data class ReversalProperties(
        var delimitingCharacters: String = ""
)