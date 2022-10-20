package com.bills.dr_stone.controllers

import com.bills.dr_stone.entities.Bill
import com.bills.dr_stone.entities.BillResponse
import com.bills.dr_stone.usecases.BillsUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bill")
class BillsController(
    val usecase: BillsUseCase,
) {
    @GetMapping
    fun listBills(): List<Bill> {
        return usecase.findAll()
    }

    @PostMapping
    fun createBill(@RequestBody bill: Bill): BillResponse? {
        return usecase.createBill(bill)
    }
}

@RestController
@RequestMapping("/bill/{billID}")
class BillsParam(
    val usecase: BillsUseCase,
) {

    @DeleteMapping
    fun deleteBill(@PathVariable billID: Int) {
        usecase.deleteBill(billID)
    }

    @GetMapping
    fun getBill(@PathVariable billID: Int): BillResponse? {
        return usecase.getBill(billID)
    }

}
