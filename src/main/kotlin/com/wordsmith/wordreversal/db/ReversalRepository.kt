package com.wordsmith.wordreversal.db

import com.wordsmith.wordreversal.model.entity.ReversalEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReversalRepository : JpaRepository<ReversalEntity, Long> {

    fun findTop5ByOrderByCreatedDesc(): List<ReversalEntity>
}