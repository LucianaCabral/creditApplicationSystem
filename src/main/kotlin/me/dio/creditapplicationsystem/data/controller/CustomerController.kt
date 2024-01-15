package me.dio.creditapplicationsystem.data.controller

import me.dio.creditapplicationsystem.data.controller.dto.CustomerDTO
import me.dio.creditapplicationsystem.data.controller.dto.CustomerView
import me.dio.creditapplicationsystem.data.dao.entity.Customer
import me.dio.creditapplicationsystem.data.service.CustomerService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customers")
class CustomerController(
    private val customerService: CustomerService
) {
    @PostMapping
    fun saveCustomer(@RequestBody customerDTO: CustomerDTO): String {
        val saveCustomer = this.customerService.save(customerDTO.toEntity())
        return "Customer ${saveCustomer.email} saved"}

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): CustomerView {
       val customer: Customer =  this.customerService.findById(id)
        return CustomerView(customer = customer)
    }

    @DeleteMapping("/{id}")
        fun delete(@PathVariable id: Long) = this. customerService.delete(id)
}