package me.dio.creditapplicationsystem.data.controller

import me.dio.creditapplicationsystem.data.controller.dto.credit.CreditDto
import me.dio.creditapplicationsystem.data.controller.dto.credit.CreditView
import me.dio.creditapplicationsystem.data.controller.dto.credit.CreditViewList
import me.dio.creditapplicationsystem.data.dao.entity.Credit
import me.dio.creditapplicationsystem.data.service.CreditService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditController(
    private val creditService: CreditService

) {
    @PostMapping
    fun saveCredit(creditDto: CreditDto): ResponseEntity<String> {
        val credit: Credit = this.creditService.save(credit = creditDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("credit ${credit.creditCode} - Customer ${credit.customer?.firstName} saved")
    }

    @GetMapping
    fun findByAllCustomerId(@RequestParam(value = "customerId") customerId: Long):
            ResponseEntity<List<CreditViewList>> {
        val creditViewList: List<CreditViewList> = this.creditService.findAllByCustomer(customerId).stream()
            .map { credit: Credit -> CreditViewList(credit) }
            .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(creditViewList)
    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(
        @RequestParam(value = "customerId") customerId: Long,
        @PathVariable creditCode: UUID
    ): ResponseEntity<CreditView> {
        val credit: Credit = this.creditService.findByCreditCode(customerId, creditCode)
        return ResponseEntity.status(HttpStatus.OK).body(CreditView(credit))
    }
}