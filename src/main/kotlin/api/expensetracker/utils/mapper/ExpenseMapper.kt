package api.expensetracker.utils.mapper

import api.expensetracker.dto.ExpenseDTO
import api.expensetracker.entity.Expense
import org.springframework.stereotype.Service

@Service
class ExpenseMapper: Mapper<ExpenseDTO, Expense>{
    override fun fromEntity(entity: Expense): ExpenseDTO = ExpenseDTO(
        entity.id,
        entity.description,
        entity.amount,
        entity.date,
        entity.category
    )

    override fun toEntity(domain: ExpenseDTO): Expense = Expense(
        domain.id,
        domain.description,
        domain.amount,
        domain.date,
        domain.category
    )
}