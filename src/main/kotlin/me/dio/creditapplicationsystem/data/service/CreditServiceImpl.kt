package me.dio.creditapplicationsystem.data.service

import me.dio.creditapplicationsystem.data.dao.entity.Credit
import me.dio.creditapplicationsystem.data.service.repository.CreditRepository
import org.springframework.stereotype.Service
import java.util.*
@Service
class CreditServiceImpl(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
) : CreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> =
        this.creditRepository.findAllByCustomer(customerId = customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit: Credit = this.creditRepository.findByCreditCode(creditCode = creditCode)
            ?: throw RuntimeException("credit code no t found $creditCode")
        return if (credit.customer?.id == customerId) credit else throw RuntimeException("Contact admin")
    }
}