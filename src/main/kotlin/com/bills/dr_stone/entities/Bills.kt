package com.bills.dr_stone.entities

import org.jetbrains.exposed.sql.Table

object Bills : Table("main.bills") {
    var id = integer("id")
    val name = varchar("name", length = 200)
}

data class Bill(
    var id: Int,
    val name: String
)

