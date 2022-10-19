package com.bills.dr_stone.repositories

import com.bills.dr_stone.entities.Bill

interface BillsRepositories {
    fun findAll() : List<Bill>
}