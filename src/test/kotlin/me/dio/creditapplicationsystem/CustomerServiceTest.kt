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
import java.util.Optional
import java.util.Random

class CustomerServiceTest {
    private val repository: CustomerRepository = mockk()
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

    @Test
    fun `should create findById by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)

        every { repository.findById(fakeId)} returns Optional.of(fakeCustomer)

        //when
        val actual: Customer = customerService.findById(fakeId)

        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { repository.findById(fakeId) }
    }
}
