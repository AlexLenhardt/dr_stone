package com.bills.dr_stone.repositories.impl

import com.bills.dr_stone.entities.Bill
import com.bills.dr_stone.entities.BillResponse
import com.bills.dr_stone.entities.Bills
import com.bills.dr_stone.entities.DATABASE_ERROR
import com.bills.dr_stone.repositories.BillsRepositories
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class BillsRepositoriesImpl : BillsRepositories {
    override fun findAll(): List<Bill> {
        val returnBills: MutableList<Bill> = mutableListOf()
        var bill: Bill

        transaction {
            Bills.selectAll().forEach {
                bill = Bill(
                    id = it[Bills.id],
                    name = it[Bills.name],
                )
                returnBills.add(bill)
            }
        }

        return returnBills.toList()
    }


    override fun createBill(bill: Bill) {
        transaction {
            Bills.insert {
                it[name] = bill.name
            }
        }
    }

    override fun deleteBill(billID: Int) {
        transaction {
            Bills.deleteWhere {
                Op.build {
                    id.eq(billID)
                }
            }
        }
    }

    override fun getBill(billID: Int): BillResponse {
        var bill = BillResponse()
        try {
            transaction {
                Bills.select {
                    Bills.id.eq(billID)
                }.forEach {
                    bill.bill = Bill(
                        id = it[Bills.id],
                        name = it[Bills.name]
                    )
                }
            }
        } catch (e: Exception) {
            BillResponse(error = DATABASE_ERROR)
        }

        return bill
    }

}