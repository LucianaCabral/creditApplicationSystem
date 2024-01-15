package me.dio.creditapplicationsystem.data.service.repository

import me.dio.creditapplicationsystem.data.dao.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<Customer,Long> {

}