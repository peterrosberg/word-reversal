package com.wordsmith.wordreversal.service

import com.wordsmith.wordreversal.db.DatabaseService
import com.wordsmith.wordreversal.db.ReversalEntity
import com.wordsmith.wordreversal.logic.WordReversalService
import com.wordsmith.wordreversal.model.PreviousTranslation
import com.wordsmith.wordreversal.model.ReversalInput
import com.wordsmith.wordreversal.model.ReversalResult
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