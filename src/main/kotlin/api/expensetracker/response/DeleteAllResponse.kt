package api.expensetracker.response

import api.expensetracker.dto.ExpenseDTO

data class DeleteAllResponse(
    val message : String,
    val expenses : List<ExpenseDTO>
)