package api.expensetracker.service

import api.expensetracker.dto.ExpenseDTO
import api.expensetracker.exceptions.ExpenseException
import api.expensetracker.repository.ExpenseRepository
import api.expensetracker.utils.mapper.ExpenseMapper
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExpenseServiceImpl (
    private val expenseRepository : ExpenseRepository,
    private val expenseMapper : ExpenseMapper
) : ExpenseService {
    override fun createExpense(expenseDTO: ExpenseDTO) : ExpenseDTO {

        if(expenseDTO.id != null) {
            throw ExpenseException("Id should not be provided in the request body. It must be null.")
        }

        val expenseEntity = expenseMapper.toEntity(expenseDTO)
        expenseRepository.save(expenseEntity)
        return expenseMapper.fromEntity(expenseEntity)
    }

    override fun getAllExpenses(): List<ExpenseDTO> {
        val expenses = expenseRepository.findAllMovies()

        if(expenses.isEmpty()){
            throw ExpenseException("List of expenses is empty.")
        }

        return expenses.map {
            expenseMapper.fromEntity(it)
        }
    }

    override fun deleteAllExpenses(): List<ExpenseDTO> {
        expenseRepository.deleteAll()
        val expenses = expenseRepository.findAllMovies()

        if(expenses.isNotEmpty()){
            throw ExpenseException("Expenses couldn't be deleted. Something went wrong")
        }
        return expenses.map {
            expenseMapper.fromEntity(it)
        }
    }

    override fun getExpense(id: UUID): ExpenseDTO {
        val expense = expenseRepository.findById(id).orElseThrow { ExpenseException("Expense with ID $id not found") }
        return expenseMapper.fromEntity(expense)
    }

    override fun deleteExpense(id: UUID) {
        val expenseExists = expenseRepository.existsById(id)

        if(!expenseExists){
            throw ExpenseException("Expense with $id not found.")
        }

        expenseRepository.deleteById(id)
    }

    override fun updateExpense(expenseDTO: ExpenseDTO): ExpenseDTO {
        val expenseExists = expenseRepository.existsById(expenseDTO.id!!)

        if(!expenseExists){
            throw ExpenseException("Expense with id $expenseDTO.id does not exists.")
        }

        if(expenseDTO.description!! == ""){
            throw ExpenseException("Complete expense object is expected.")
        }
        expenseRepository.save(expenseMapper.toEntity(expenseDTO))
        return expenseDTO
    }
}