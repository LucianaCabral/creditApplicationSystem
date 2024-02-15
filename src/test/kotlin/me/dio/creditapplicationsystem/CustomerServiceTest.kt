package me.dio.creditapplicationsystem

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import me.dio.creditapplicationsystem.StubCustomer.buildCustomer
import me.dio.creditapplicationsystem.data.dao.entity.Customer
import me.dio.creditapplicationsystem.data.service.CustomerServiceImpl
import me.dio.creditapplicationsystem.data.service.repository.CustomerRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

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
}