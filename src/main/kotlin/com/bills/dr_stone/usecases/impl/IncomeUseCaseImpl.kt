package com.bills.dr_stone.usecases.impl

import com.bills.dr_stone.entities.DATABASE_ERROR
import com.bills.dr_stone.entities.Income
import com.bills.dr_stone.entities.IncomeResponse
import com.bills.dr_stone.repositories.IncomeRepository
import com.bills.dr_stone.usecases.IncomeUseCase
import org.springframework.stereotype.Service

@Service
class IncomeUseCaseImpl(
    var repo: IncomeRepository
): IncomeUseCase {
    override fun createIncome(income: Income): IncomeResponse {
        return try {
            repo.createIncome(income)
        }catch (e: Exception){
            var error = DATABASE_ERROR
            error.description = e.toString()
            IncomeResponse(error = error)
        }
    }
}