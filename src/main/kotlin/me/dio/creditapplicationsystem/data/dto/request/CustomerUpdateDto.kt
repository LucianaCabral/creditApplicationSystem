package me.dio.creditapplicationsystem.data.dto.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.creditapplicationsystem.data.dao.entity.Customer
import java.math.BigDecimal

data class CustomerUpdateDto(
    @field:NotEmpty(message = "Add first name") val firstName: String,
    @field:NotEmpty(message = "Add last name") val lastName: String,
    @field:NotNull(message = "Invalid Input") val income: BigDecimal,
    @field:NotEmpty(message = "Add your street") val street: String,
    @field:NotEmpty(message = "Add your zip code") val zipCode: String,
) {
    fun toEntity(customer: Customer): Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.street = this.street
        customer.address.zipCode = this.zipCode
        return customer
    }
}
