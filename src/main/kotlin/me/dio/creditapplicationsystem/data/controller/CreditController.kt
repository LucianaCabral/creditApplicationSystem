package me.dio.creditapplicationsystem.data.controller

import me.dio.creditapplicationsystem.data.controller.dto.CreditDto
import me.dio.creditapplicationsystem.data.controller.dto.CreditViewList
import me.dio.creditapplicationsystem.data.dao.entity.Credit
import me.dio.creditapplicationsystem.data.service.CreditService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditController(
    private val creditService: CreditService

) {
    @PostMapping
    fun saveCredit(creditDto: CreditDto): String {
        val credit: Credit = this.creditService.save(credit = creditDto.toEntity())
        return "credit ${credit.creditCode} - Customer ${credit.customer?.firstName} saved"
    }

    @GetMapping
    fun findByAllCustomerId(customerId: Long): List<CreditViewList> {
        return this.creditService.findAllByCustomer(customerId = customerId).stream()
            .map { credit: Credit -> CreditViewList(credit) }.collect(Collectors.toList())
    }
}