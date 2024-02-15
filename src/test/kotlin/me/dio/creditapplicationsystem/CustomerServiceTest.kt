package me.dio.creditapplicationsystem

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import me.dio.creditapplicationsystem.data.dao.entity.Address
import me.dio.creditapplicationsystem.data.dao.entity.Customer
import me.dio.creditapplicationsystem.data.service.CustomerService
import me.dio.creditapplicationsystem.data.service.CustomerServiceImpl
import me.dio.creditapplicationsystem.data.service.repository.CustomerRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class CustomerServiceTest {
    private val repository : CustomerRepository = mockk()
    private val customerService = CustomerServiceImpl(repository)

    @Test
    fun `should create customer`() {
        //given
        val fakeCustomer: Customer = buildCustomer()
        every { repository.save(fakeCustomer) } returns fakeCustomer

        //when
        val actual: Customer = customerService.save(fakeCustomer)

        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { repository.save(fakeCustomer) }
    }

    private fun buildCustomer(
        firstName: String = "Cami",
        lastName: String = "Cavalcante",
        cpf: String = "28475934625",
        email: String = "camila@gmail.com",
        password: String = "12345",
        zipCode: String = "12345",
        street: String = "Rua da Cami",
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