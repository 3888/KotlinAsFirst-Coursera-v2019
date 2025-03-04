@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import lesson1.task1.trackLength
import java.time.YearMonth
import kotlin.math.abs

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая -
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {

    val isHappy = number.div(1000) + number.div(100).rem(10) == number.div(10).rem(10) + number.rem(10)

//    val numbers = number.toString().map { it.toString().toInt() }
//    val isHappy =  numbers[0] + numbers[1] == numbers[2] + numbers[3]

    return isHappy
}


/**
 * Простая +
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean =
    when {
        (abs(x1 - x2) == abs(y1 - y2)) -> true
        (x1 == x2 || y1 == y2) -> true
        else -> false
    }


/**
 * Простая +
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {
    return YearMonth.of(year, month).lengthOfMonth()
}

/**
 * Средняя +
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean {
    val d = trackLength(x1, y1, x2, y2)

    return d <= r2 - r1
}

/**
 * Средняя +
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
    val holeSquare = r * s
    val brickSquareFace = a * b
    val brickSquareSideFace = a * c
    val brickSquareOnEdge = b * c

    var appropriateSize = false

    if (brickSquareFace <= holeSquare) {
        appropriateSize = true
    }
    if (brickSquareSideFace <= holeSquare) {
        appropriateSize = true
    }
    if (brickSquareOnEdge <= holeSquare) {
        appropriateSize = true
    }

    return appropriateSize
}
