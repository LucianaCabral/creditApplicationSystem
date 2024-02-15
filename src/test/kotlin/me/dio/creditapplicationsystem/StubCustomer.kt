package me.dio.creditapplicationsystem

import me.dio.creditapplicationsystem.data.dao.entity.Address
import me.dio.creditapplicationsystem.data.dao.entity.Credit
import me.dio.creditapplicationsystem.data.dao.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

internal object StubCustomer {

     fun buildCustomer(
        firstName: String = "Lorem",
        lastName: String = "Ipsum",
        cpf: String = "28475934625",
        email: String = "loremIpsum@gmail.com",
        password: String = "12345",
        zipCode: String = "12345",
        street: String = "Rua das Palmeiras",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
        id: Long = 1L
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        address = Address(
            zipCode = zipCode,
            street = street,
        ),
        income = income,
        id = id
    )

}
