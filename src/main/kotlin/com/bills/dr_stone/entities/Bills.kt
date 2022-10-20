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

data class BillResponse(
    var bill: Bill? = null,
    val error: GenericError? = null
)

val ERROR_NAME_EMPTY = BillsResponse(
    moduleName = "BILL",
    code = "EMPTY_NAME",
    description = "Name cannot be empty"
)

val DATABASE_ERROR: GenericError = BillsResponse(
    moduleName = "DATABASE",
    code = "DATABASE_ERROR",
    description = "An error occurs on database"
)

class BillsResponse(
    moduleName: String,
    code: String,
    description: String
): GenericError(moduleName, code, description)