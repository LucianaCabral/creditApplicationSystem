package me.dio.creditapplicationsystem.data.service

import me.dio.creditapplicationsystem.data.dao.entity.Customer
import me.dio.creditapplicationsystem.data.service.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(
    private val customerRepository: CustomerRepository
) : CustomerService {
    override fun save(customer: Customer): Customer =
        customerRepository.save(customer)

    override fun findById(id: Long): Customer = this.customerRepository.findById(id).orElseThrow() {
        throw RuntimeException("Id $id not found")
    }

    override fun delete(id: Long) = this.customerRepository.deleteById(id)
}

