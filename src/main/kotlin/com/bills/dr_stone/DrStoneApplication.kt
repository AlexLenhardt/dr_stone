package com.bills.dr_stone

import org.jetbrains.exposed.sql.Database
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DrStoneApplication

fun main(args: Array<String>) {
    val conn = DB
    conn.connect()
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