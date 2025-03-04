@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример +
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая +
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    val ageLastNumber = age % 10
    var old = ""
    val isExclusion = age % 100 in 11..14

    if (ageLastNumber == 1)
        old = "год";
    else if (ageLastNumber == 0 || ageLastNumber in 5..9)
        old = "лет";
    else if (ageLastNumber in 2..4)
        old = "года";
    if (isExclusion)
        old = "лет";
    return "$age $old"
}


/**
 * Простая +
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s1 = t1 * v1
    val s2 = t2 * v2
    val s3 = t3 * v3
    val s = (s1 + s2 + s3) / 2

    val tArray = arrayOf(t1, t2, t3)
    val vArray = arrayOf(v1, v2, v3)
    val sArray = arrayOf(s1, s2, s3)

    var halfTime = 0.0
    var index = 0
    var wayValue = 0.0

    while (wayValue < s) {
        wayValue += sArray[index]
        index += 1
    }

    if (index > 0) index -= 1

    for (i in 0..index) {
        halfTime += tArray[i]
    }

    halfTime -= (wayValue - s) / vArray[index]

    return halfTime
}

/**
 * Простая +
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    var danger = 0

    if (kingX == rookX1 || kingY == rookY1) {
        danger = 1
    }

    if (kingX == rookX2 || kingY == rookY2) {
        danger = 2
    }

    if ((kingX == rookX1 || kingY == rookY1) && (kingX == rookX2 || kingY == rookY2)) {
        danger = 3
    }

    return danger
}

/**
 * Простая -
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int = when {
    (abs(kingX - bishopX) == abs(kingY - bishopY)) && (kingX == rookX || kingY == rookY) -> 3
    kingX == rookX || kingY == rookY -> 1
    abs(kingX - bishopX) == abs(kingY - bishopY) -> 2
    else -> 0
}
//{
//    var res = 0
//    var danger = 0
//
//    if (abs(kingX - bishopX) == abs(kingY - bishopY)) {
//        danger = 2
//    }
//    if (kingX == rookX || kingY == rookY) {
//        danger = 1
//    }
//
//    if ((abs(kingX - bishopX) == abs(kingY - bishopY)) && (kingX == rookX || kingY == rookY)) {
//        danger = 3
//    }
//    return danger
//}

/**
 * Простая +
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val sidesArray = arrayOf(a, b, c)
    sidesArray.sort()

    val sideA = sidesArray[0]
    val sideB = sidesArray[1]
    val sideC = sidesArray[2]

    var triangleType = 0

    when {
        sideA + sideB <= sideC -> triangleType = -1 // Triangle inequality
        sqr(sideA) + sqr(sideB) > sqr(sideC) -> triangleType = 0
        sqr(sideA) + sqr(sideB) == sqr(sideC) -> triangleType = 1 // Pythagorean theorem
        sqr(sideA) + sqr(sideB) < sqr(sideC) -> triangleType = 2
    }

    return triangleType
}

/**
 * Средняя -
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    return when {
        c > b || d < a -> -1
        else -> abs (max(a,c) - min(b,d))
    }
}
