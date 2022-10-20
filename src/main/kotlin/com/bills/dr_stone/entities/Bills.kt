package com.bills.dr_stone.entities

import org.h2.util.DateTimeUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object Bills : Table("bills") {
    var id = integer("id").autoIncrement()
    var title = varchar("title", length = 200)
    var value = long("value")
    var dueDate = datetime("due_date")
    var isRecurrent = bool("is_recurrent")

    override val primaryKey = PrimaryKey(id)
}

data class Bill(
    var id: Int,
    var title: String,
    var value: Long,
    var dueDate: DateTimeUtils,
    var isRecurrent: Boolean
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