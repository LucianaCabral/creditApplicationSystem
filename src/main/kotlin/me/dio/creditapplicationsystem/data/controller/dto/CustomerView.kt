package me.dio.creditapplicationsystem.data.controller.dto

import me.dio.creditapplicationsystem.data.dao.entity.Address
import me.dio.creditapplicationsystem.data.dao.entity.Customer
import java.math.BigDecimal

data class CustomerView(
    val firsName: String,
    val lastName: String,
    val cpf: String,
    val income: BigDecimal,
    val email: String,
    val street: String,
    val zipCode: String,
) {
    constructor(customer:Customer): this (
        firsName = customer.firstName,
       lastName= customer.lastName,
        cpf = customer.cpf,
        income = customer.income,
        email = customer.email,
        street = customer.address.street,
        zipCode = customer.address.zipCode
    )

}
