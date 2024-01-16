package me.dio.creditapplicationsystem.data.exception

import java.time.LocalDateTime

data class ExceptionsDetails(
    val title:String,
    val timeStamp: LocalDateTime,
    val status: Int,
    val exception: String,
    val details: MutableMap<String,String?>
)
