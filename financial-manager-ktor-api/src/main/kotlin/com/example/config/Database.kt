package com.example.config

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import com.example.models.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object Database {
    fun init() {
        val database = Database.connect(
            url = "jdbc:h2:file:./build/db",
            user = "root",
            driver = "org.h2.Driver",
            password = ""
        )

        transaction(database) {
            SchemaUtils.create(FinancesTable)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}