package me.dio.creditapplicationsystem.data.dto.response

import me.dio.creditapplicationsystem.data.dao.entity.Credit
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

data class CreditViewList(
    val creditCode: UUID,
    val dayFirstOfInstallment: LocalDate,
    val numberOfInstallment: Int,
) {
    constructor(credit: Credit) : this(
        creditCode = credit.creditCode,
        dayFirstOfInstallment = credit.dayFirstInstallment,
        numberOfInstallment = credit.numberOfInstallment,
    )
}
