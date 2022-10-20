package com.bills.dr_stone.usecases

import com.bills.dr_stone.entities.Bill
import com.bills.dr_stone.entities.CreateBillResponse

interface BillsUseCase {
    fun findAll(): List<Bill>
    fun createBill(bill: Bill): CreateBillResponse?
    fun deleteBill(billID: Int)
}