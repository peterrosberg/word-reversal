package com.pepp.wordreversal.service

import com.pepp.wordreversal.db.DatabaseService
import com.pepp.wordreversal.db.ReversalEntity
import com.pepp.wordreversal.logic.WordReversalService
import com.pepp.wordreversal.model.PreviousTranslation
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

        if (input.sentence.isNotEmpty()) {
            databaseService.save(ReversalEntity(input.sentence, result))
        }

        return ReversalResult(result)
    }

    fun getLastReversals(): List<PreviousTranslation> {
        return databaseService.getLatest().map { entity ->
            PreviousTranslation(result = entity.result, sentence = entity.input, time = entity.created)
        }
    }

}