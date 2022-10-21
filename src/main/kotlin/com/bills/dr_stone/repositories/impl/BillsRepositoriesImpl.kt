package com.bills.dr_stone.repositories.impl

import com.bills.dr_stone.entities.Bill
import com.bills.dr_stone.entities.BillResponse
import com.bills.dr_stone.entities.DATABASE_ERROR
import com.bills.dr_stone.repositories.BillsRepositories
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import java.time.LocalDate
import com.bills.dr_stone.entities.BillsTable as BillsDB

@Repository
class BillsRepositoriesImpl : BillsRepositories {
    override fun findAll(): List<Bill> {
        val returnBills: MutableList<Bill> = mutableListOf()
        var bill: Bill

        transaction {
            BillsDB.select {
                Op.build {
                    BillsDB.dueDate greaterEq LocalDate.now()
                }
            }.orderBy(BillsDB.dueDate, SortOrder.ASC).forEach {
                bill = Bill(
                    id = it[BillsDB.id],
                    title = it[BillsDB.title],
                    code = it[BillsDB.code],
                    dueDate = it[BillsDB.dueDate],
                    isRecurrent = it[BillsDB.isRecurrent],
                    value = it[BillsDB.value],
                )
                returnBills.add(bill)
            }
        }

        return returnBills.toList()
    }

    override fun createBill(bill: Bill) {
        transaction {
            BillsDB.insert {
                it[title] = bill.title
                it[code] = bill.code
                it[dueDate] = bill.dueDate
                it[isRecurrent] = bill.isRecurrent
                it[value] = bill.value
            }
        }
    }

    override fun deleteBill(billID: Int) {
        transaction {
            BillsDB.deleteWhere {
                Op.build {
                    id.eq(billID)
                }
            }
        }
    }

    override fun getBill(billID: Int): BillResponse {
        val bill = BillResponse()
        try {
            transaction {
                BillsDB.select {
                    BillsDB.id.eq(billID)
                }.forEach {
                    bill.bill = Bill(
                        id = it[BillsDB.id],
                        title = it[BillsDB.title],
                        code = it[BillsDB.code],
                        dueDate = it[BillsDB.dueDate],
                        isRecurrent = it[BillsDB.isRecurrent],
                        value = it[BillsDB.value]
                    )
                }
            }
        } catch (e: Exception) {
            BillResponse(error = DATABASE_ERROR)
        }

        return bill
    }

    override fun updateBill(bill: Bill): BillResponse? {
        transaction {
            BillsDB.update({
                BillsDB.id eq bill.id
            }) {
                it[title] = bill.title
                it[value] = bill.value
                it[dueDate] = bill.dueDate
                it[isRecurrent] = bill.isRecurrent
            }
        }

        return BillResponse(bill = bill)
    }

}