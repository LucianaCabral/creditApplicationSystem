package me.dio.creditapplicationsystem.data.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.creditapplicationsystem.data.dao.entity.Address
import me.dio.creditapplicationsystem.data.dao.entity.Customer
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDTO(
    @field:NotEmpty(message = "Add first name") val firstName: String,
    @field:NotEmpty(message = "Add last name") val lastName: String,
    @field:NotEmpty(message = "Invalid Input")
    @field:CPF(message = "This invalid CPF") val cpf: String,
    @field:NotNull(message = "Invalid Input") val income: BigDecimal,
    @field:Email(message = "Invalid valid")
    @field:NotEmpty(message = "Add your valid") val email: String,
    @field:NotEmpty(message = "Add your password") val password: String,
    @field:NotEmpty(message = "Add street address") val street: String,
    @field:NotEmpty(message = "Add zip code name") val zipCode: String,
) {
    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(street = street, zipCode = zipCode),
    )
}
