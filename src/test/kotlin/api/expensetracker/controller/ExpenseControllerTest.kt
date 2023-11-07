package api.expensetracker.controller

import api.expensetracker.ExpenseController
import api.expensetracker.dto.ExpenseDTO
import api.expensetracker.service.ExpenseService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate
import java.util.UUID

@WebMvcTest(ExpenseController::class)
class ExpenseControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var expenseServiceMock: ExpenseService

    @Test
    fun createExpense() {
        val expenseDTO = ExpenseDTO(
            id = null,
            description = "Sample Expense",
            amount = 100.0,
            date = LocalDate.now(),
            category = "Sample Category"
        )

        every { expenseServiceMock.createExpense(any()) } returns expenseDTO

        // When
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/expenses/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "description": "Sample Expense",
                        "amount": 100.0,
                        "date": "2023-11-05",
                        "category": "Sample Category"
                    }
                    """
                )
        )
            .andExpect(status().isCreated)

    }

    @Test
    fun getAllExpenses() {

        val expenseDTOs = listOf(
            ExpenseDTO(
                id = UUID.randomUUID(),
                description = "Expense 1",
                amount = 50.0,
                date = LocalDate.now(),
                category = "Category 1"
            ),
            ExpenseDTO(
                id = UUID.randomUUID(),
                description = "Expense 2",
                amount = 110.0,
                date = LocalDate.now(),
                category = "Category 2"
            ),
        )

        every { expenseServiceMock.getAllExpenses() } returns expenseDTOs

        mockMvc.perform(MockMvcRequestBuilders.get("/api/expenses/"))
            .andExpect(status().isOk)

    }

    @Test
    fun deleteAllExpenses() {
        every { expenseServiceMock.deleteAllExpenses() } returns emptyList()

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/expenses/"))
            .andExpect(status().isOk)

    }

    @Test
    fun getExpense() {
        val expenseId = UUID.randomUUID()
        val expenseDTO = ExpenseDTO(
            id = expenseId,
            description = "Sample Expense",
            amount = 100.0,
            date = LocalDate.now(),
            category = "Sample Category"
        )

        every { expenseServiceMock.getExpense(expenseId) } returns expenseDTO

        mockMvc.perform(MockMvcRequestBuilders.get("/api/expenses/$expenseId"))
            .andExpect(status().isOk)

    }

    @Test
    fun deleteExpense() {
        val expenseId = UUID.randomUUID()

        every { expenseServiceMock.deleteExpense(expenseId) } just runs

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/expenses/$expenseId"))
            .andExpect(status().isOk)

        verify(exactly = 1) { expenseServiceMock.deleteExpense(expenseId) }
    }
}
