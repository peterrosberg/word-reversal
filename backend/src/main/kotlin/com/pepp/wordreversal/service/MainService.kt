package com.pepp.wordreversal.service

import com.pepp.wordreversal.db.DatabaseService
import com.pepp.wordreversal.logic.WordReversalService
import com.pepp.wordreversal.model.ReversalInput
import com.pepp.wordreversal.model.ReversalResult
import org.springframework.stereotype.Service

@Service
class MainService(
        val wordReversalService: WordReversalService,
        val databaseService: DatabaseService
) {

    fun reverseWords(input: ReversalInput) : ReversalResult {
        return ReversalResult("")
    }

    fun getLastReversals(): List<String> {
        return emptyList()
    }

}