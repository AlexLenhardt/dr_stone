package com.bills.dr_stone.repositories

import com.bills.dr_stone.entities.Income
import com.bills.dr_stone.entities.IncomeResponse

interface IncomeRepository {
    fun createIncome(income: Income): IncomeResponse
}