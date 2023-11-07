import api.expensetracker.dto.ExpenseDTO
import api.expensetracker.entity.Expense
import api.expensetracker.exceptions.ExpenseException
import api.expensetracker.repository.ExpenseRepository
import api.expensetracker.service.ExpenseServiceImpl
import api.expensetracker.utils.mapper.ExpenseMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.time.LocalDate
import java.util.*

class ExpenseServiceImplTest {
    @InjectMocks
    private lateinit var expenseService: ExpenseServiceImpl

    @Mock
    private lateinit var expenseRepository: ExpenseRepository

    @Mock
    private lateinit var expenseMapper: ExpenseMapper

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testCreateExpense() {
        val expenseDTO = ExpenseDTO(null, "Test Expense", 100.0, LocalDate.now(), "Test Category")
        val expenseEntity = Expense(null, "Test Expense", 100.0, LocalDate.now(), "Test Category")

        Mockito.`when`(expenseMapper.toEntity(expenseDTO)).thenReturn(expenseEntity)
        Mockito.`when`(expenseRepository.save(expenseEntity)).thenReturn(expenseEntity)
        Mockito.`when`(expenseMapper.fromEntity(expenseEntity)).thenReturn(expenseDTO)

        val result = expenseService.createExpense(expenseDTO)

        assert(result == expenseDTO)
        Mockito.verify(expenseMapper, Mockito.times(1)).toEntity(expenseDTO)
        Mockito.verify(expenseRepository, Mockito.times(1)).save(expenseEntity)
        Mockito.verify(expenseMapper, Mockito.times(1)).fromEntity(expenseEntity)
    }

    @Test
    fun testGetAllExpenses() {
        val expenseEntities = listOf(
            Expense(UUID.randomUUID(), "Expense 1", 100.0, LocalDate.now(), "Category 1"),
            Expense(UUID.randomUUID(), "Expense 2", 200.0, LocalDate.now(), "Category 2")
        )

        Mockito.`when`(expenseRepository.findAllMovies()).thenReturn(expenseEntities)
        val expenseDTOs = expenseEntities.map { expenseMapper.fromEntity(it) }

        val result = expenseService.getAllExpenses()

        assert(result == expenseDTOs)
        Mockito.verify(expenseRepository, Mockito.times(1)).findAllMovies()
    }

    @Test
    fun testDeleteAllExpenses() {
        Mockito.`when`(expenseRepository.findAllMovies()).thenReturn(emptyList()) // Simulate an empty list of expenses
        Mockito.`when`(expenseRepository.deleteAll()).then {
            Mockito.`when`(expenseRepository.findAllMovies()).thenReturn(emptyList())
            null
        }

        val result = expenseService.deleteAllExpenses()

        assert(result.isEmpty())
        Mockito.verify(expenseRepository, Mockito.times(1)).deleteAll()
        Mockito.verify(expenseRepository, Mockito.times(1)).findAllMovies()
    }

    @Test
    fun testGetExpense() {
        val expenseId = UUID.randomUUID()
        val expenseEntity = Expense(expenseId, "Test Expense", 100.0, LocalDate.now(), "Test Category")

        Mockito.`when`(expenseRepository.findById(expenseId)).thenReturn(Optional.of(expenseEntity))
        val expenseDTO = expenseMapper.fromEntity(expenseEntity)

        val result = expenseService.getExpense(expenseId)

        assert(result == expenseDTO)
    }

    @Test
    fun testDeleteExpense() {
        val expenseId = UUID.randomUUID()
        Mockito.`when`(expenseRepository.existsById(expenseId)).thenReturn(true)

        expenseService.deleteExpense(expenseId)

        Mockito.verify(expenseRepository, Mockito.times(1)).deleteById(expenseId)
    }

    @Test
    fun testDeleteNonExistingExpense() {
        val expenseId = UUID.randomUUID()
        Mockito.`when`(expenseRepository.existsById(expenseId)).thenReturn(false)

        try {
            expenseService.deleteExpense(expenseId)
        } catch (e: ExpenseException) {
            assert(e.message == "Expense with $expenseId not found.")
        }
    }
}
