package com.example.dao

import com.example.models.Finance
import kotlinx.serialization.Serializable

@Serializable
data class FinanceSave(
    val name: String,
    val amount: Float,
    val day: Int,
    val type: String
)

interface DAOFinancesFacade {
    suspend fun financesFindAll(): List<Finance>
    suspend fun financesCreate(payload: FinanceSave): Int
    suspend fun financesUpdate(id: Int, payload: FinanceSave)
    suspend fun financesDelete(id: Int)
}