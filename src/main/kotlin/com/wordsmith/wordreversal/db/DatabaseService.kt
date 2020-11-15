package com.wordsmith.wordreversal.db

import org.springframework.stereotype.Service

@Service
class DatabaseService (
        val repository: ReversalRepository
) {

    fun save(reversalEntity: ReversalEntity): ReversalEntity {
        return repository.save(reversalEntity)
    }

    fun getLatest(): List<ReversalEntity> {
        return repository.findTop5ByOrderByCreatedDesc()
    }

}