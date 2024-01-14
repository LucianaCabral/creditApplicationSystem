package me.dio.creditapplicationsystem.entity

import me.dio.creditapplicationsystem.enumeration.Status
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

data class Credits (
    val creditCode: UUID = UUID.randomUUID(),
    val creditValue: BigDecimal = BigDecimal.ZERO,
    val dayFirstInstallment: LocalDate,
    val numberOfInstallment: Int = 0,
    val status: Status = Status.INPROGRESS,
    val customer: Customer? = null,
    val id: Long? = null

)
