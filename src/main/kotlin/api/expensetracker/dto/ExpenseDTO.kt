package api.expensetracker.dto

import jakarta.validation.constraints.*
import java.util.UUID
import java.time.LocalDate

data class ExpenseDTO(
    val id:UUID?,

    @field:NotBlank(message = "Description is required")
    val description: String = "",

    @field:PositiveOrZero(message = "Amount must be positive or zero")
    val amount: Double,

    @field:NotNull(message = "Date is required")
    @field:PastOrPresent(message = "Date must be in the past")
    val date: LocalDate,

    @field:Size(max = 255, message = "Category can have at most 255 characters")
    val category: String? = ""
)
