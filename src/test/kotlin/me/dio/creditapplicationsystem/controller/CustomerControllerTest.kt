package me.dio.creditapplicationsystem.controller

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.validation.Valid
import me.dio.creditapplicationsystem.StubCustomer
import me.dio.creditapplicationsystem.StubCustomer.buildCustomer
import me.dio.creditapplicationsystem.data.dao.entity.Customer
import me.dio.creditapplicationsystem.data.dto.request.CustomerDTO
import me.dio.creditapplicationsystem.data.dto.request.CustomerUpdateDto
import me.dio.creditapplicationsystem.data.dto.response.CustomerView
import me.dio.creditapplicationsystem.data.service.CustomerService
import me.dio.creditapplicationsystem.data.service.repository.CustomerRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.util.Random

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration

class CustomerControllerTest {
    @Autowired
    private lateinit var customerRepository: CustomerRepository
    @Autowired private lateinit var mockMvc: MockMvc
    @Autowired private lateinit var objectMapper: ObjectMapper

    companion object {
        const val URL: String = "/api/customers"
    }

    @BeforeEach
    fun setup() = customerRepository.deleteAll()

    @AfterEach
    fun tearDown() = customerRepository.deleteAll()

    @Test
    fun `should create a customer and return 201 status`() {
        //given
        val customerDto: CustomerDTO = buildCustomerDTO()
        val valueAsString: String = objectMapper.writeValueAsString(customerDto)

        //when


        //then
        mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(valueAsString))
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Lorem"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Ipsum"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("28475934625"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("loremIpsum@gmail.com"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.zipCode").value("12345"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("Rua das Palmeiras"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not save a customer with same CPF and return 409 status`() {
        //given
        customerRepository.save(buildCustomerDTO().toEntity())
        val customerDto: CustomerDTO = buildCustomerDTO()
        val valueAsString: String = objectMapper.writeValueAsString(customerDto)
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(valueAsString))
            .andExpect(MockMvcResultMatchers.status().isConflict)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Conflit! Consult the Documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timeStamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(409))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.exception")
                    .value("DataIntegrityViolationException")
            )
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not save a customer with empty firstName and return 400 status`() {
        //given
        val customerDto: CustomerDTO = buildCustomerDTO(firstName = "")
        val valueAsString: String = objectMapper.writeValueAsString(customerDto)
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
            .content(valueAsString)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request! Consult the Documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timeStamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.exception")
                    .value("MethodArgumentNotValidException")
            )
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should find customer by id and return 200 status`() {
        //given
        val customer: Customer = customerRepository.save(buildCustomerDTO().toEntity())
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("$URL/${customer.id}")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Lorem"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Ipsum"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("28475934625"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("loremIpsum@gmail.com"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.zipCode").value("12345"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("Rua das Palmeiras"))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not find customer whith invalid id and return 400 status`() {
        //given
        val invalidId: Long = 2L
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/$invalidId")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request! Consult the Documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timeStamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.exception")
                    .value("BusinessException")
            )
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should delete customer by id and return 204 status`() {
        //given
        val customer: Customer = customerRepository.save(buildCustomerDTO().toEntity())
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.delete("$URL/${customer.id}")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNoContent)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not delete customer by id and return 400 status`() {
        //given
        val invalidId: Long = Random().nextLong()
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.delete("$URL/${invalidId}")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request! Consult the Documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timeStamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.exception")
                    .value("BusinessException")
            )
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should update a customer and return 200 status`() {
        //given
        val customer: Customer = customerRepository.save(buildCustomerDTO().toEntity())
        val customerUpdateDto: CustomerUpdateDto = builderCustomerUpdateDto()
        val valueAsString: String = objectMapper.writeValueAsString(customerUpdateDto)
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.patch("$URL?customerId=${customer.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("LoremUpdate"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("IpsumUpdate"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("28475934625"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("loremIpsum@gmail.com"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.income").value("5000.0"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.zipCode").value("update"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("Rua Updated"))
            //.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not update a customer with invalid id and return 400 status`() {
        //given
        val invalidId: Long = Random().nextLong()
        val customerUpdateDto: CustomerUpdateDto = builderCustomerUpdateDto()
        val valueAsString: String = objectMapper.writeValueAsString(customerUpdateDto)
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.patch("$URL?customerId=$invalidId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request! Consult the Documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timeStamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.exception")
                    .value("BusinessException")
            )
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty)
            .andDo(MockMvcResultHandlers.print())
    }




    fun buildCustomerDTO(
        firstName: String = "Lorem",
        lastName: String = "Ipsum",
        cpf: String = "28475934625",
        email: String = "loremIpsum@gmail.com",
        password: String = "12345",
        zipCode: String = "12345",
        street: String = "Rua das Palmeiras",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
    ) = CustomerDTO(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        income = income,
        email = email,
        password = password,
        street = street,
        zipCode = zipCode,
    )

    private fun builderCustomerUpdateDto(
        firstName: String = "LoremUpdate",
        lastName: String = "IpsumUpdate",
        income: BigDecimal = BigDecimal.valueOf(5000.0),
        zipCode: String = "update",
        street: String = "Rua Updated"
    ): CustomerUpdateDto = CustomerUpdateDto(
        firstName = firstName,
        lastName = lastName,
        income = income,
        zipCode = zipCode,
        street = street
    )
}