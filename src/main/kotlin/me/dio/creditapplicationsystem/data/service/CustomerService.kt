package me.dio.creditapplicationsystem.data.service

import me.dio.creditapplicationsystem.model.entity.Customer

interface CustomerService {
    fun save(customer: Customer) : Customer
    fun findById(customer: Customer):Customer
    fun delete(id:Long): Customer
}