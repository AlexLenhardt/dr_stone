package com.bills.dr_stone.controllers

import com.bills.dr_stone.entities.Bill
import com.bills.dr_stone.usecases.BillsUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bills")
class BillsController(
    val usecase: BillsUseCase,
) {
    @GetMapping
    fun listBills(): List<Bill> {
        return usecase.findAll()
    }
}
