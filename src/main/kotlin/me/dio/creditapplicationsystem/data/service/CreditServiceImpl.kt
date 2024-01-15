package me.dio.creditapplicationsystem.data.service

import me.dio.creditapplicationsystem.model.entity.Credit
import me.dio.creditapplicationsystem.repository.CreditRepository
import java.util.*

class CreditServiceImpl(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
): CreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = credit.id?.let { customerService.findById(it) }
        }
        return creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> {
        TODO("Not yet implemented")
    }

    override fun findByCreditCode(creditCode: UUID): Credit {
        TODO("Not yet implemented")
    }
}