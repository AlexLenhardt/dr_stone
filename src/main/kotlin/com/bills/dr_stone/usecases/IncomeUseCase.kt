package com.bills.dr_stone.usecases

import com.bills.dr_stone.entities.Income
import com.bills.dr_stone.entities.IncomeResponse

interface IncomeUseCase {
    fun createIncome(income: Income): IncomeResponse
}
