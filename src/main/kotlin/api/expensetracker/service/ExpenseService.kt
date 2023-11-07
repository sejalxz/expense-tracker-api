package api.expensetracker.service

import api.expensetracker.dto.ExpenseDTO
import java.util.UUID

interface ExpenseService {
    fun createExpense(expenseDTO: ExpenseDTO) : ExpenseDTO
    fun getAllExpenses(): List<ExpenseDTO>
    fun deleteAllExpenses(): List<ExpenseDTO>
    fun getExpense(id: UUID) : ExpenseDTO
    fun deleteExpense(id: UUID)
    fun updateExpense(expenseDTO: ExpenseDTO) : ExpenseDTO
}