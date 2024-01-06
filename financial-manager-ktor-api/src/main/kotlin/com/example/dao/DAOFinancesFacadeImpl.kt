package com.example.dao

import com.example.config.Database.dbQuery
import com.example.models.Finance
import com.example.models.FinancesTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOFinancesFacadeImpl() : DAOFinancesFacade {
    private fun resultRowToArticle(row: ResultRow) = Finance(
        id = row[FinancesTable.id],
        name = row[FinancesTable.name],
        amount = row[FinancesTable.amount],
        day = row[FinancesTable.day],
        type = row[FinancesTable.type],
    )

    override suspend fun financesFindAll(): List<Finance> = dbQuery {
        FinancesTable.selectAll().map(::resultRowToArticle)
    }

    override suspend fun financesCreate(payload: FinanceSave): Int = dbQuery {
        FinancesTable.insert {
            it[name] = payload.name
            it[amount] = payload.amount
            it[day] = payload.day
            it[type] = payload.type
        }[FinancesTable.id]
    }

    override suspend fun financesUpdate(id: Int, payload: FinanceSave): Unit = dbQuery {
        FinancesTable.update({ FinancesTable.id eq id }) {
            it[name] = payload.name
            it[amount] = payload.amount
            it[day] = payload.day
            it[type] = payload.type
        }
    }

    override suspend fun financesDelete(id: Int): Unit = dbQuery {
        FinancesTable.deleteWhere { FinancesTable.id.eq(id) }
    }

}

