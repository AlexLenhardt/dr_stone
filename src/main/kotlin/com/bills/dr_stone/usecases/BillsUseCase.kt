package com.bills.dr_stone.usecases

import com.bills.dr_stone.entities.Bill
import com.bills.dr_stone.entities.BillResponse

interface BillsUseCase {
    fun findAll(): List<Bill>
    fun createBill(bill: Bill): BillResponse?
    fun deleteBill(billID: Int)
    fun getBill(billID: Int): BillResponse?
    fun updateBill(bill: Bill): BillResponse?
}