class QuestaoListas {

    fun last(list: List<Int>) = list.last()

    fun penultimate(list: List<Int>) = list[list.size - 2]

    fun isPalindrome(list: List<Int>) = list == list.reversed()

    fun encode(list: List<Char>): List<Pair<Int, Char>> {
        if (list.isEmpty()) return emptyList()
        val result = mutableListOf<Pair<Int, Char>>()
        var count = 1
        for (i in 1 until list.size) {
            if (list[i] == list[i - 1]) {
                count++
            } else {
                result.add(Pair(count, list[i - 1]))
                count = 1
            }
        }
        result.add(Pair(count, list.last()))
        return result
    }

    fun decode(list: List<Pair<Int, Char>>): List<Char> {
        return list.flatMap { (count, char) -> List(count) { char } }
    }

}

fun main() {
    val q = QuestaoListas()
    println(q.last(listOf(1, 1, 2, 3, 5, 8)))                      // 8
    println(q.penultimate(listOf(1, 1, 2, 3, 5, 8)))              // 5
    println(q.isPalindrome(listOf(1, 2, 3, 2, 1)))                // true
    println(q.encode("aaaabccaadeeee".toList()))                 // [(4, a), (1, b), (2, c), (2, a), (1, d), (4, e)]
    println(
        q.decode(
            listOf(
                4 to 'a',
                1 to 'b',
                2 to 'c',
                2 to 'a',
                1 to 'd',
                4 to 'e'
            )
        )
    ) // [a, a, a, a, b, c, c, a, a, d, e, e, e, e]

}