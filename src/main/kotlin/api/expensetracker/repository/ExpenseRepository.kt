package api.expensetracker.repository

import api.expensetracker.entity.Expense
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface ExpenseRepository: CrudRepository<Expense, UUID>{
    @Query(value = "SELECT expense FROM Expense as expense")
    fun findAllMovies() : List<Expense>
}