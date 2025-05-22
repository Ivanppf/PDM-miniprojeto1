fun Int.isPrime(): Boolean {
    if (this < 2) return false
    for (i in 2..Math.sqrt(this.toDouble()).toInt()) {
        if (this % i == 0) return false
    }
    return true
}

fun gcd(a: Int, b: Int): Int {
    return if (b == 0) a else gcd(b, a % b)
}

fun listPrimesInRange(range: IntRange): List<Int> {
    return range.filter { it.isPrime() }
}

fun main() {
    println(7.isPrime())                          // true
    println(gcd(36, 63))                          // 9
    println(listPrimesInRange(7..31))            // [7, 11, 13, 17, 19, 23, 29, 31]
}