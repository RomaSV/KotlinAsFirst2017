@file:Suppress("UNUSED_PARAMETER", "unused")
package lesson7.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E
    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)
    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    val result = MatrixImpl<E>(height, width)
    for (w in 0 until width) {
        for (h in 0 until height) {
            result[Cell(h, w)] = e
        }
    }
    return result
}

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int) : Matrix<E> {

    private val values = mutableMapOf<Cell, E>()

    override fun get(row: Int, column: Int): E  = values[Cell(row, column)]!!

    override fun get(cell: Cell): E  = values[cell]!!

    override fun set(row: Int, column: Int, value: E) {
        values[Cell(row, column)] = value
    }

    override fun set(cell: Cell, value: E) {
        values[cell] = value
    }

    private fun valuesEquals(m1: MatrixImpl<*>, m2: MatrixImpl<*>): Boolean {
        for (row in 0 until m1.height) {
            for (column in 0 until m1.width) {
                if (m1[row, column] != m2[row, column]) return false
            }
        }
        return true
    }

    override fun equals(other: Any?) =
            other is MatrixImpl<*> &&
            height == other.height &&
            width == other.width &&
            valuesEquals(this, other)

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + width
        result = 31 * result + values.hashCode()
        return result
    }

    override fun toString(): String {
        val result = StringBuilder()
        for (column in 0 until height) {
            for (row in 0 until width) {
                result.append(values[Cell(row, column)])
                if (row != width - 1) result.append(", ")
            }
            result.append("\n")
        }
        return result.toString()
    }
}

