package com.bills.dr_stone.controllers

import com.bills.dr_stone.entities.Bill
import com.bills.dr_stone.entities.CreateBillResponse
import com.bills.dr_stone.usecases.BillsUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bills/{billID}")
class BillsController(
    val usecase: BillsUseCase,
) {
    @GetMapping
    fun listBill(): List<Bill> {
        return usecase.findAll()
    }

    @PostMapping
    fun createBill(@RequestBody bill: Bill): CreateBillResponse? {
        return usecase.createBill(bill)
    }

    @DeleteMapping
    fun deleteBill(@PathVariable billID: Int) {
        usecase.deleteBill(billID)
    }

}
