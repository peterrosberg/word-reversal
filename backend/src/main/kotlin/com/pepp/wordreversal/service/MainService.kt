package com.pepp.wordreversal.service

import com.pepp.wordreversal.db.DatabaseService
import com.pepp.wordreversal.db.ReversalEntity
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

        val result = wordReversalService.reverseWords(input.sentence)

        databaseService.save(ReversalEntity(input.sentence, result))

        return ReversalResult(result)
    }

    fun getLastReversals(): List<String> {
        return databaseService.getLatest().map { it.result }
    }

}