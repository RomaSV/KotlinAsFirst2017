@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1


/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n/2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else   -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var num = Math.abs(n)
    if(num == 0) return 1
    while(num > 0){
        count++
        num /= 10
    }
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int = if(n > 2) fib(n - 2) + fib(n - 1) else 1

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */

fun nod(m: Int, n: Int): Int {
    var a = m
    var b = n
    var tmp: Int

    while(b > 0) {
        a %= b
        tmp = a
        a = b
        b = tmp
    }
    return a
}

fun lcm(m: Int, n: Int): Int = m * n / nod(m, n)


/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var isFound = false
    var i = 2
    while(!isFound) {
        if(n % i == 0) isFound = true else i++
    }
    return i
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var isFound = false
    var i = n - 1
    while(!isFound) {
        if(n % i == 0) isFound = true else i--
    }
    return i
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    for (i in 2..Math.min(m, n)) {
        if(m % i == 0 && n % i == 0) return false

    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for(i in m..n) {
        val root = Math.sqrt(i.toDouble())
        if(root / root.toInt() == 1.0) return true
    }
    return false
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */

fun trig(type: Char, x: Double, eps: Double): Double {
    //Уменьшаем X
    var smallX = x
    if(smallX != 0.0) {
        smallX /= Math.PI
        while (smallX % 5 == 0.0) smallX /= 5
        smallX *= Math.PI
    }

    //Выставляем параметры для sin или cos
    var a: Double
    var res: Double
    var i: Int
    var j = -1

    when (type) {
        'S' -> {
            a = smallX
            res = smallX
            i = 3
        }
        'C' -> {
            a = smallX
            res = 1.0
            i = 2
        }
        else -> {
            a = 0.0
            res = 0.0
            i = 0
        }
    }

    while(Math.abs(a) >= eps) {
        //Считаем а по частям на случай больших факториалов/степеней
        a = 1.0
        for(k in 0 until i) {
            a *= smallX / (i - k)
        }
        res += a * j
        i += 2
        j *= -1
    }

    return res
}

fun sin(x: Double, eps: Double): Double = trig('S', x, eps)

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double = trig('C', x, eps)


/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var num = n
    var len = 0
    var res = 0

    while(num > 0) {
        num /= 10
        len++
    }
    num = n

    for(i in len-1 downTo 0) {
        res += (num % 10) * Math.pow(10.0, i.toDouble()).toInt()
        num /= 10
    }
    return res
}
/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean {
    var num = n
    var len = 0

    while(num != 0) {
        num /= 10
        len++
    }

    num = n
    for(i in 0 until len/2) {
        val l = (len - 2 * i).toDouble()
        if(num % 10 != num / Math.pow(10.0, l - 1).toInt()) return false
        num %= Math.pow(10.0, l - 1).toInt()
        num /= 10
    }

    return true
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var num = n
    val digit = num % 10
    while(num > 0) {
        if(num % 10 != digit) return true
        num /= 10
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var i = 1
    var j = 1.0
    while(i <= n){
        var quad = Math.pow(j, 2.0).toInt()
        var rQuad = revert(quad)

        while(quad % 10 == 0) {
            if(i == n) return 0
            i++
            quad /= 10
        }

        while(rQuad > 0) {
            if(i == n) return rQuad % 10
            i++
            rQuad /= 10

        }
        j++
    }
    return -1
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var tmp1 = 1
    var tmp2 = 1
    var i = 3

    while(i <= n && n !in 1..2 ) {
        var num = tmp1 + tmp2
        var rnum = revert(num)

        tmp1 = tmp2
        tmp2 = num

        while(num % 10 == 0) {
            if(i == n) return 0
            i++
            num /= 10
        }

        while(rnum > 0) {
            if(i == n) return rnum % 10
            i++
            rnum /= 10

        }

    }
    return 1
}

