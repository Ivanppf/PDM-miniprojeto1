fun <T : Comparable<T>> Tree<T>.insert(newValue: T): Tree<T> = when (this) {
    is End -> Node(newValue)
    is Node -> if (newValue < value) Node(value, left.insert(newValue), right)
    else Node(value, left, right.insert(newValue))

    else -> this
}

fun <T> Tree<T>.leafCount(): Int = when (this) {
    is End -> 0
    is Node -> if (left is End && right is End) 1
    else left.leafCount() + right.leafCount()

    else -> 0
}

fun <T> Tree<T>.leafValues(): List<T> = when (this) {
    is End -> emptyList()
    is Node -> if (left is End && right is End) listOf(value)
    else left.leafValues() + right.leafValues()

    else -> emptyList()
}

fun <T> Tree<T>.convertToString(): String = when (this) {
    is End -> ""
    is Node -> {
        val leftStr = left.convertToString()
        val rightStr = right.convertToString()
        if (leftStr.isEmpty() && rightStr.isEmpty()) "$value"
        else "$value(${leftStr},${rightStr})"
    }

    else -> ""
}

fun String.convertToTree(): Tree<String> {
    var index = 0

    fun parse(): Tree<String> {
        if (index >= length) return End

        fun parseValue(): String {
            val start = index
            while (index < length && this[index].isLetter()) index++
            return substring(start, index)
        }

        val value = parseValue()
        if (index >= length || this[index] != '(') return Node(value)

        index++
        val left = parse()
        if (this[index] == ',') index++
        val right = parse()
        index++

        return Node(value, left, right)
    }

    return parse()
}

fun main() {
    val t1 = Node("a", Node("b", Node("d"), Node("e")), Node("c", End, Node("f", Node("g"), End)))
    println(t1.convertToString()) // a(b(d,e),c(,f(g,)))

    val parsed = "a(b(d,e),c(,f(g,)))".convertToTree()
    println(parsed) // T(a T(b T(d) T(e)) T(c . T(f T(g) .)))

    println(Node("x", Node("x"), End).leafCount()) // 1

    val t2 = Node("a", Node("b"), Node("c", Node("d"), Node("e")))
    println(t2.leafValues()) // [b, d, e]

    val insertedTree = End.insert(10).insert(5).insert(15).insert(3)
    println(insertedTree) // T(10 T(5 T(3) .) T(15))

}