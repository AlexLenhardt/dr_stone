package com.bills.dr_stone.repositories.impl

import com.bills.dr_stone.entities.Income
import com.bills.dr_stone.entities.IncomeResponse
import com.bills.dr_stone.repositories.IncomeRepository
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import com.bills.dr_stone.entities.IncomeTable as IncomeDB

@Repository
class IncomeRepositoryImpl: IncomeRepository {
    override fun createIncome(income: Income): IncomeResponse {
        transaction {
            IncomeDB.insert {
                it[title] = income.title
                it[value] = income.value
                it[receiptDate] = income.receiptDate
                it[isRecurrent] = income.isRecurrent
            }
        }
        return IncomeResponse(income = income)
    }
}