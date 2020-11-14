package com.pepp.wordreversal.model

import java.time.ZonedDateTime

data class PreviousTranslation(
        val sentence: String,
        val result: String,
        val time: ZonedDateTime
)