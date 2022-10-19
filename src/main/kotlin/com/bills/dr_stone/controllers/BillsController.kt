package com.bills.dr_stone.controllers

import com.bills.dr_stone.entities.Bill
import com.bills.dr_stone.entities.CreateBill
import com.bills.dr_stone.usecases.BillsUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bills")
class BillsController(
    val usecase: BillsUseCase,
) {
    @GetMapping
    fun listBill(): List<Bill> {
        return usecase.findAll()
    }

    @PostMapping
    fun createBill(@RequestBody bill: Bill): CreateBill? {
        return usecase.createBill(bill)
    }
}
