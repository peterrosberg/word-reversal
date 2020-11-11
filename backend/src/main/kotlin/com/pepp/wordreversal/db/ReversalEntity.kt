package com.pepp.wordreversal.db

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class ReversalEntity(

        @Column(nullable = false)
        val input: String,

        @Column(nullable = false)
        val result: String

) {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long = 0

        @CreationTimestamp
        @Column(nullable = false, updatable = false)
        lateinit var created: LocalDateTime
}
