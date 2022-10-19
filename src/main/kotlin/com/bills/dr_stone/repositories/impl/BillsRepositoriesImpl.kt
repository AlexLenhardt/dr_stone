package com.bills.dr_stone.repositories.impl

import com.bills.dr_stone.entities.Bill
import com.bills.dr_stone.entities.Bills
import com.bills.dr_stone.repositories.BillsRepositories
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Repository

@Repository
class BillsRepositoriesImpl: BillsRepositories {
    override fun findAll(): List<Bill> {
        val returnBills:  MutableList<Bill> = mutableListOf()
        var bill : Bill

        Bills.selectAll().forEach {
            bill = Bill(
                id = it[Bills.id],
                name = it[Bills.name],
            )
            returnBills.add(bill)
        }

        return returnBills.toList()
    }

}