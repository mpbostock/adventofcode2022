package mpbostock

object Day03 {
    fun List<String>.commonChar(): Char {
        return this.fold(this.first().toSet()) { acc, str -> acc intersect str.toSet() }.first()
    }
    fun String.chopInHalf(): List<String> {
        val half = this.length / 2
        return listOf(this.take(half), this.drop(half))
    }
    object Items {
        private val lowercasePriorities = ('a'..'z').zip(1..26).toMap()
        private val uppercasePriorities = ('A'..'Z').zip(27..52).toMap()
        private val allItemPriorities = lowercasePriorities + uppercasePriorities
        fun priorityFor(item: Char): Int = allItemPriorities[item]!!
    }

    fun partOne(input: List<String>): Int {
        return input.sumOf { Items.priorityFor(it.chopInHalf().commonChar()) }
    }

    fun partTwo(input: List<String>): Int {
        return input.windowed(3, 3).sumOf { Items.priorityFor(it.commonChar()) }
    }

    private val input = FileIO.readInput("day03input.txt") { s -> s }

    @JvmStatic
    fun main(args: Array<String>) {
        val partOneSolution = partOne(input)
        println(partOneSolution)

        val partTwoSolution = partTwo(input)
        println(partTwoSolution)
    }
}