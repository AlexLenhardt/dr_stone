package com.bills.dr_stone.entities

import org.jetbrains.exposed.sql.Table

object Bills : Table("bills") {
    var id = integer("id").autoIncrement()
    val name = varchar("name", length = 200)


    override val primaryKey = PrimaryKey(id)
}

data class Bill(
    var id: Int,
    val name: String
)

data class CreateBillResponse(
    val error: GenericError? = null
)
