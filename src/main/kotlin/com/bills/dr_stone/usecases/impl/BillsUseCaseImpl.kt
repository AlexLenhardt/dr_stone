package com.bills.dr_stone.usecases.impl

import com.bills.dr_stone.entities.Bill
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
}