package api.expensetracker.entity

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.UUID
import java.time.LocalDate
import jakarta.persistence.*

@Entity
data class Expense(

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID?,
    val description: String,
    val amount: Double,
    val date: LocalDate,
    val category: String?
)
