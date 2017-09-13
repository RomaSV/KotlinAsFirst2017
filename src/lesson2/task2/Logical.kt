@file:Suppress("UNUSED_PARAMETER")
package lesson2.task2

import lesson1.task1.sqr

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
        sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean{
    val strNumber: String = number.toString()
    val digit1: Int = strNumber[0].toInt()
    val digit2: Int = strNumber[1].toInt()
    val digit3: Int = strNumber[2].toInt()
    val digit4: Int = strNumber[3].toInt()

    return (digit1+digit2)==(digit3+digit4)
}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean{
    val dx: Int = Math.abs(x1-x2)
    val dy: Int = Math.abs(y1-y2)

    return when{
        dx == dy -> true
        dx == 0  -> true
        dy == 0  -> true
        else     -> false
    }
}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(x1: Double, y1: Double, r1: Double,
                 x2: Double, y2: Double, r2: Double): Boolean{
    val dx: Double = Math.abs(x1-x2)
    val dy: Double = Math.abs(y1-y2)
    val delta: Double = Math.sqrt(sqr(dx)+sqr(dy))

    return when{
        r1 + delta <= r2 -> true
        else             -> false
    }
}

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean = when{
    (a<=r && b<=s) || (a<=s && b<=r) -> true
    (b<=r && c<=s) || (b<=s && c<=r) -> true
    (c<=r && a<=s) || (c<=s || a<=r) -> true
    else                             ->  false
    }

