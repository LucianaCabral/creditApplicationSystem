package me.dio.creditapplicationsystem.data.exception

data class BusinessException(override val message: String) : RuntimeException(message){
}
