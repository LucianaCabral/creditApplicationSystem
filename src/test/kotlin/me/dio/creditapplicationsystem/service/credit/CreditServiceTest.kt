package me.dio.creditapplicationsystem.service.credit

import io.mockk.*
import io.mockk.junit5.MockKExtension
import me.dio.creditapplicationsystem.StubCustomer.buildCustomer
import me.dio.creditapplicationsystem.data.dao.entity.Credit
import me.dio.creditapplicationsystem.data.dao.entity.Customer
import me.dio.creditapplicationsystem.data.exception.BusinessException
import me.dio.creditapplicationsystem.data.service.CreditService
import me.dio.creditapplicationsystem.data.service.CreditServiceImpl
import me.dio.creditapplicationsystem.data.service.CustomerService
import me.dio.creditapplicationsystem.data.service.CustomerServiceImpl
import me.dio.creditapplicationsystem.data.service.repository.CreditRepository
import me.dio.creditapplicationsystem.data.service.repository.CustomerRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@ExtendWith(MockKExtension::class)
class CreditServiceTest {

    private val creditRepository: CreditRepository = mockk()
    private val customerRepository: CustomerRepository = mockk()
    private val customerService = CustomerServiceImpl(customerRepository)
    private val creditService = CreditServiceImpl(creditRepository,customerService)


    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should create credit`() {
        //given
        val credit: Credit = buildCredit()
        val customerId: Long = 1L

        every { customerService.findById(customerId) } returns credit.customer!!
        every { creditRepository.save(credit) } returns credit

        //when
        val actual: Credit = creditRepository.save(credit)

        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(credit)
        verify(exactly = 1) { creditRepository.save(credit) }
    }

    @Test
    fun `should return list of credits for a customer`() {
        //given
        val customerId: Long = 1L
        val expectedCredits: List<Credit> = listOf(buildCredit(), buildCredit(), buildCredit())

        every { creditRepository.findAllByCustomer(customerId) } returns expectedCredits
        //when
        val actual: List<Credit> = creditService.findAllByCustomer(customerId)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isNotEmpty
        Assertions.assertThat(actual).isSameAs(expectedCredits)

        verify(exactly = 1) { creditRepository.findAllByCustomer(customerId) }
    }

    @Test
    fun `should return credit for a valid customer and credit code`() {
        //given
        val customerId: Long = 1L
        val creditCode: UUID = UUID.randomUUID()
        val credit: Credit = buildCredit(customer = Customer(id = customerId))

        every { creditRepository.findByCreditCode(creditCode) } returns credit
        //when
        val actual: Credit = creditService.findByCreditCode(customerId, creditCode)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(credit)

        verify(exactly = 1) { creditRepository.findByCreditCode(creditCode) }
    }

    @Test
    fun `should throw BusinessException for invalid credit code`() {
        //given
        val customerId: Long = 1L
        val invalidCreditCode: UUID = UUID.randomUUID()

        every { creditRepository.findByCreditCode(invalidCreditCode) } returns null
        //when
        //then
        Assertions.assertThatThrownBy {
            creditService.findByCreditCode(
                customerId,
                invalidCreditCode
            )
        }
            .isInstanceOf(BusinessException::class.java)
            .hasMessage("credit code not found $invalidCreditCode")
        //then
        verify(exactly = 1) { creditRepository.findByCreditCode(invalidCreditCode) }
    }

    @Test
    fun `should throw IllegalArgumentException for different customer ID`() {
        //given
        val customerId: Long = 1L
        val creditCode: UUID = UUID.randomUUID()
        val credit: Credit = buildCredit(customer = Customer(id = 2L))

        every { creditRepository.findByCreditCode(creditCode) } returns credit
        //when
        //then
        Assertions.assertThatThrownBy { creditService.findByCreditCode(customerId, creditCode) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Contact admin")

        verify { creditRepository.findByCreditCode(creditCode) }
    }

    companion object {
        private fun buildCredit(
            creditValue: BigDecimal = BigDecimal.valueOf(100.0),
            dayFirstInstallment: LocalDate = LocalDate.now().plusMonths(2L),
            numberOfInstallments: Int = 15,
            customer: Customer = buildCustomer()
        ): Credit = Credit(
            creditValue = creditValue,
            dayFirstInstallment = dayFirstInstallment,
            numberOfInstallment = numberOfInstallments,
            customer = customer
        )
    }
}