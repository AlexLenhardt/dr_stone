package com.bills.dr_stone.repositories.impl

import com.bills.dr_stone.entities.Bill
import com.bills.dr_stone.entities.BillResponse
import com.bills.dr_stone.entities.Bills
import com.bills.dr_stone.entities.DATABASE_ERROR
import com.bills.dr_stone.repositories.BillsRepositories
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class BillsRepositoriesImpl : BillsRepositories {
    override fun findAll(): List<Bill> {
        val returnBills: MutableList<Bill> = mutableListOf()
        var bill: Bill

        transaction {
            Bills.select {
                Op.build {
                    Bills.dueDate greaterEq LocalDate.now()
                }
            }.orderBy(Bills.dueDate, SortOrder.ASC).forEach {
                bill = Bill(
                    id = it[Bills.id],
                    title = it[Bills.title],
                    code = it[Bills.code],
                    dueDate = it[Bills.dueDate],
                    isRecurrent = it[Bills.isRecurrent],
                    value = it[Bills.value],
                )
                returnBills.add(bill)
            }
        }

        return returnBills.toList()
    }

    override fun createBill(bill: Bill) {
        transaction {
            Bills.insert {
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
            Bills.deleteWhere {
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
                Bills.select {
                    Bills.id.eq(billID)
                }.forEach {
                    bill.bill = Bill(
                        id = it[Bills.id],
                        title = it[Bills.title],
                        code = it[Bills.code],
                        dueDate = it[Bills.dueDate],
                        isRecurrent = it[Bills.isRecurrent],
                        value = it[Bills.value]
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
            Bills.update({
                Bills.id eq bill.id
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