package me.dio.creditapplicationsystem.data.controller

import jakarta.websocket.server.PathParam
import me.dio.creditapplicationsystem.data.controller.dto.CustomerDTO
import me.dio.creditapplicationsystem.data.controller.dto.CustomerUpdateDto
import me.dio.creditapplicationsystem.data.controller.dto.CustomerView
import me.dio.creditapplicationsystem.data.dao.entity.Customer
import me.dio.creditapplicationsystem.data.service.CustomerService
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    fun saveCustomer(@RequestBody customerDTO: CustomerDTO): ResponseEntity<String> {
        val saveCustomer = this.customerService.save(customerDTO.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer ${saveCustomer.email} saved")
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customer = customer))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = this.customerService.delete(id)

    @PatchMapping
    fun updateCustomer(
        @PathParam(value = "customrId") id: Long,
        @RequestBody customerUpdateDto: CustomerUpdateDto
    ): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id)
        val customerToUpDate: Customer = customerUpdateDto.toEntity(customer)
        val costumerUpdate: Customer = this.customerService.save(customerToUpDate)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customer = costumerUpdate))
    }
}
