package com.bills.dr_stone.entities

import com.fasterxml.jackson.annotation.JsonFormat
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import java.time.LocalDate

object Bills : Table("bills") {
    var id = integer("id").autoIncrement()
    var code = varchar("code", length = 200)
    var title = varchar("title", length = 200)
    var value = double("value")
    var dueDate = date("due_date")
    var isRecurrent = bool("is_recurrent")

    override val primaryKey = PrimaryKey(id)
}

data class Bill(
    var id: Int,
    var code: String,
    var title: String,
    var value: Double,

    @JsonFormat(pattern = "yyyy-MM-dd")
    var dueDate: LocalDate,

    var isRecurrent: Boolean,

    var isOverdue: Boolean = false
)

data class BillResponse(
    var bill: Bill? = null,
    val error: GenericError? = null
)

class BillsResponse(
    moduleName: String,
    code: String,
    description: String
): GenericError(moduleName, code, description)

val ERROR_NAME_EMPTY = BillsResponse(
    moduleName = "BILL",
    code = "EMPTY_NAME",
    description = "Name cannot be empty"
)

val DATABASE_ERROR = BillsResponse(
    moduleName = "DATABASE",
    code = "DATABASE_ERROR",
    description = "An error occurs on database"
)

val ERROR_VALUE_ZEROS = BillsResponse(
    moduleName = "BILL",
    code = "EMPTY_VALUE",
    description = "Value cannot be zeros"
)
val ERROR_INCORRECT_DATE = BillsResponse(
    moduleName = "BILL",
    code = "INCORRECT_DATE",
    description = "Incorrect date"
)
