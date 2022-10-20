package com.bills.dr_stone.usecases.impl

import com.bills.dr_stone.entities.Bill
import com.bills.dr_stone.entities.BillResponse
import com.bills.dr_stone.entities.DATABASE_ERROR
import com.bills.dr_stone.entities.ERROR_NAME_EMPTY
import com.bills.dr_stone.repositories.BillsRepositories
import com.bills.dr_stone.usecases.BillsUseCase
import org.springframework.stereotype.Service

@Service
class BillsUseCaseImpl(
    private val repo: BillsRepositories,
) : BillsUseCase {

    override fun findAll(): List<Bill> {
        return repo.findAll()
    }


    override fun createBill(bill: Bill): BillResponse? {
        if (bill.name.trim() == "") {
            return BillResponse(error = ERROR_NAME_EMPTY)
        }

        try {
            repo.createBill(bill)
        }catch (e: Exception){
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
}