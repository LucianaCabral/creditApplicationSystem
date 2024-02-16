package me.dio.creditapplicationsystem.data.dto.response

import me.dio.creditapplicationsystem.data.dao.entity.Customer
import java.math.BigDecimal

data class CustomerView(
    val firstName: String,
    val lastName: String,
    val cpf: String,
    val income: BigDecimal,
    val email: String,
    val street: String,
    val zipCode: String,
    val id: Long?
) {
    constructor(customer: Customer) : this(
        firstName = customer.firstName,
        lastName = customer.lastName,
        cpf = customer.cpf,
        income = customer.income,
        email = customer.email,
        street = customer.address.street,
        zipCode = customer.address.zipCode,
        id = customer.id
    )

}
