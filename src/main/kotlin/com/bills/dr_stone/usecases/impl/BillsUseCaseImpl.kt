package com.bills.dr_stone.usecases.impl

import com.bills.dr_stone.entities.*
import com.bills.dr_stone.repositories.BillsRepositories
import com.bills.dr_stone.usecases.BillsUseCase
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class BillsUseCaseImpl(
    private val repo: BillsRepositories,
) : BillsUseCase {

    override fun findAll(): List<Bill> {
        return repo.findAll()
    }

    override fun createBill(bill: Bill): BillResponse? {
        if (bill.title.trim() == "") {
            return BillResponse(error = ERROR_NAME_EMPTY)
        }

        if (bill.value == 0.0) {
            return BillResponse(error = ERROR_VALUE_ZEROS)
        }

        if (bill.dueDate.isBefore(LocalDate.now())) {
            return BillResponse(error = ERROR_INCORRECT_DATE)
        }

        try {
            repo.createBill(bill)
        } catch (e: Exception) {
            return BillResponse(error = DATABASE_ERROR)
        }

        return null
    }

    override fun deleteBill(billID: Int) {
        repo.deleteBill(billID)
    }

    override fun getBill(billID: Int): BillResponse {
        return repo.getBill(billID)
    }

    override fun updateBill(bill: Bill): BillResponse? {
        val oldBill = repo.getBill(bill.id).bill
        if (oldBill!!.dueDate.isBefore(LocalDate.now())) {
            return BillResponse(error = ERROR_INCORRECT_DATE)
        }

        return try {
            repo.updateBill(bill)
        } catch (e: Exception) {
            BillResponse(error = DATABASE_ERROR)
        }
    }
}