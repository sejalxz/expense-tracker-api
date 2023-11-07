package api.expensetracker

import api.expensetracker.dto.ExpenseDTO
import api.expensetracker.response.DeleteAllResponse
import api.expensetracker.service.ExpenseService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.UUID


@RestController
@RequestMapping("/api/expenses/")
@Validated
class ExpenseController(
    private val expenseService: ExpenseService
) {
    @PostMapping
    fun createExpense(@RequestBody @Valid expenseDTO: ExpenseDTO): ResponseEntity<ExpenseDTO> {
        return ResponseEntity(expenseService.createExpense(expenseDTO), HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllExpenses(): ResponseEntity<List<ExpenseDTO>> = ResponseEntity.ok(expenseService.getAllExpenses())

    @DeleteMapping
    fun deleteAllExpenses(): ResponseEntity<DeleteAllResponse> {
        return ResponseEntity(DeleteAllResponse("Expenses deleted successfully.",expenseService.deleteAllExpenses() ), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getExpense(@PathVariable id: UUID): ResponseEntity<ExpenseDTO> = ResponseEntity(expenseService.getExpense(id), HttpStatus.OK)

    @DeleteMapping("/{id}")
    fun deleteExpense(@PathVariable id: UUID): ResponseEntity<String> {
        expenseService.deleteExpense(id)
        return ResponseEntity("Expense deleted successfully.", HttpStatus.OK)
    }

    @PutMapping
    fun updateExpense(@RequestBody expenseDTO: ExpenseDTO): ResponseEntity<ExpenseDTO> {
        return ResponseEntity(expenseService.updateExpense(expenseDTO), HttpStatus.OK)
    }

}