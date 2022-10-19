package com.bills.dr_stone.controllers

import com.bills.dr_stone.entities.Bill
import com.bills.dr_stone.repositories.BillsRepositories
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bills")
class BillsController(
    val repo: BillsRepositories,
) {
    @GetMapping
    fun listBills(): List<Bill> {
        val conn = DB
        conn.connect()

        var list: List<Bill> = listOf()
        transaction {
            list = repo.findAll()
        }
        return list
    }
}

object DB {
    private val host = System.getenv("DB_HOST") ?: "localhost"
    private val port = System.getenv("DB_PORT")?.toIntOrNull() ?: 5432
    private val dbName = System.getenv("DB_NAME") ?: "billsDB"
    private val dbUser = System.getenv("DB_USER") ?: "postgres"
    private val dbPassword = System.getenv("DB_PASSWORD") ?: "root"
    //establish database connection
    fun connect() = Database.connect(
        url = "jdbc:postgresql://$host:$port/$dbName",
        driver = "org.postgresql.Driver",
        user = dbUser,
        password = dbPassword,
    )
}
