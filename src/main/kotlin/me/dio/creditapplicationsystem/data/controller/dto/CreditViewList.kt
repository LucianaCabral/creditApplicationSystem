package me.dio.creditapplicationsystem.data.controller.dto

import me.dio.creditapplicationsystem.data.dao.entity.Credit
import java.math.BigDecimal
import java.time.LocalDate

data class CreditViewList(
    val creditValue: BigDecimal,
    val dayFirstOfInstallment: LocalDate,
    val numberOfInstallment: Int,
    val customId: Long
) {
    constructor(credit: Credit) : this(
        creditValue = credit.creditValue,
        dayFirstOfInstallment = credit.dayFirstInstallment,
        numberOfInstallment = credit.numberOfInstallment,
        customId = credit.id ?: 0
    )
}
