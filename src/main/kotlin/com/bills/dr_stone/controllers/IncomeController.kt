package com.bills.dr_stone.controllers

import com.bills.dr_stone.entities.Income
import com.bills.dr_stone.entities.IncomeResponse
import com.bills.dr_stone.usecases.IncomeUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/income")
class IncomeController(
    var usecase: IncomeUseCase,
) {

    @PostMapping
    fun createIncome(@RequestBody income: Income): IncomeResponse?{
        return usecase.createIncome(income)
    }
}