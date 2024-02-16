package me.dio.creditapplicationsystem

import me.dio.creditapplicationsystem.data.dao.entity.Address
import me.dio.creditapplicationsystem.data.dao.entity.Credit
import me.dio.creditapplicationsystem.data.dao.entity.Customer
import me.dio.creditapplicationsystem.data.dto.request.CustomerDTO
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Month

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



    fun buildCredit(
        creditValue: BigDecimal = BigDecimal.valueOf(500.0),
        dayFirstInstallment: LocalDate = LocalDate.of(2023, Month.APRIL, 22),
        numberOfInstallments: Int = 5,
        customer: Customer
    ): Credit = Credit(
        creditValue = creditValue,
        dayFirstInstallment = dayFirstInstallment,
        numberOfInstallment = numberOfInstallments,
        customer = customer
    )
}
