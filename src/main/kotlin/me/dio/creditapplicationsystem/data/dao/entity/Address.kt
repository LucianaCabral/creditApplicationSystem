package me.dio.creditapplicationsystem.data.dao.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Address (
    @Column(nullable = false) var street: String = "",
    @Column(nullable = false) var zipCode: String = ""
)
