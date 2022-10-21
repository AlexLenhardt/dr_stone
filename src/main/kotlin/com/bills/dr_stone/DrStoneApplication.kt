package com.bills.dr_stone

import com.bills.dr_stone.entities.BillsTable
import com.bills.dr_stone.entities.IncomeTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DrStoneApplication

fun main(args: Array<String>) {
    val database = DB.connect()
    transaction(database) {
        SchemaUtils.create(BillsTable)
        SchemaUtils.create(IncomeTable)
    }
    runApplication<DrStoneApplication>(*args)
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