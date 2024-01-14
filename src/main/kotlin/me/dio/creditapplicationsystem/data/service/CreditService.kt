package me.dio.creditapplicationsystem.data.service

import me.dio.creditapplicationsystem.model.entity.Credit
import java.util.UUID

interface CreditService {
    fun save(credit:Credit): Credit
    fun findAllByCustomer(customerId: Long): List<Credit>
    fun findByCreditCode(creditCode:UUID): Credit
}