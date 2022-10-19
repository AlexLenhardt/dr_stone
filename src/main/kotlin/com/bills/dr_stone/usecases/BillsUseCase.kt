package com.bills.dr_stone.usecases

import com.bills.dr_stone.entities.Bill

interface BillsUseCase {
    fun findAll(): List<Bill>
}