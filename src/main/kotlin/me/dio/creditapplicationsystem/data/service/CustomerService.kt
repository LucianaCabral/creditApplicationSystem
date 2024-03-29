package me.dio.creditapplicationsystem.data.service

import me.dio.creditapplicationsystem.data.dao.entity.Customer

interface CustomerService {
    fun save(customer: Customer) : Customer
    fun findById(id: Long) : Customer
    fun delete(id:Long)
}