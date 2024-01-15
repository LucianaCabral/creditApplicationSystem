package me.dio.creditapplicationsystem.data.controller

import me.dio.creditapplicationsystem.data.controller.dto.CustomerDTO
import me.dio.creditapplicationsystem.data.service.CustomerService
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
}