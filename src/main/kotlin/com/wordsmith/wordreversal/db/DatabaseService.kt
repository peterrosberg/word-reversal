package com.wordsmith.wordreversal.db

import com.wordsmith.wordreversal.model.entity.ReversalEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DatabaseService(
        val repository: ReversalRepository
) {

    var latestCache: List<ReversalEntity> = listOf()
    var cacheUpToDate = false

    fun save(reversalEntity: ReversalEntity): ReversalEntity {
        log.debug("Saving reversal result $reversalEntity")
        cacheUpToDate = false
        return repository.save(reversalEntity)
    }

    fun getLatest(): List<ReversalEntity> {
        if (cacheUpToDate) {
            log.debug("Returning latest from cache")
        } else {
            log.debug("Refreshing cache by getting latest from DB")
            latestCache = repository.findTop5ByOrderByCreatedDesc()
            log.debug("Successful cache update")
            cacheUpToDate = true
        }

        return latestCache
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(DatabaseService::class.java)
    }
}