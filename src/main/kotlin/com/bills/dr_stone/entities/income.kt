package com.bills.dr_stone.entities

import com.fasterxml.jackson.annotation.JsonFormat
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDate
import java.time.LocalDateTime

object IncomeTable : Table() {
    var id = integer("id").autoIncrement()
    var title = varchar("title", length = 200)
    var value = double("value")
    var receiptDate = date("receiptDate")
    var isRecurrent = bool("is_recurrent")
    var statusCode = integer("status_code").default(0)
    var createdAt = datetime("created_at").default(LocalDateTime.now())
    var modifiedAt = datetime("modified_at").default(LocalDateTime.now())

    override val primaryKey = PrimaryKey(id)
}

data class Income(
    var id: Int,
    var title: String,
    var value: Double,

    @JsonFormat(pattern = "yyyy-MM-dd")
    var receiptDate: LocalDate,

    var isRecurrent: Boolean,
)

class IncomeResponse(
    var income: Income? = null,
    val error: GenericError? = null
)

class ErrorIncome(
    moduleName: String,
    code: String,
    description: String
): GenericError(moduleName, code, description)
