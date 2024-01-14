package me.dio.creditapplicationsystem.repository

import me.dio.creditapplicationsystem.model.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CreditRepository: JpaRepository<Credit,Long> {

}