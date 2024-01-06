package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

@Serializable
data class Finance(
    val id: Int,
    val name: String,
    val amount: Float,
    val day: Int,
    val type: String,
)

object FinancesTable : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    val amount = float("amount")
    val day = integer("day")
    val type = varchar("type", 255)
}