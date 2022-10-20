package com.bills.dr_stone.repositories

import com.bills.dr_stone.entities.Bill
import com.bills.dr_stone.entities.BillResponse

interface BillsRepositories {
    fun findAll() : List<Bill>
    fun createBill(bill: Bill)
    fun deleteBill(billID: Int)
    fun getBill(billID: Int): BillResponse
    fun updateBill(bill: Bill): BillResponse?
}