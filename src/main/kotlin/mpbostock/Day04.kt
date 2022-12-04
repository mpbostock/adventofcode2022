package mpbostock

object Day04 {
    fun IntRange.Companion.fromList(ints: List<Int>): IntRange = IntRange(ints[0], ints[1])
    fun IntRange.fullyContains(otherRange: IntRange): Boolean = this.contains(otherRange.first) && this.contains(otherRange.last)
    fun IntRange.partiallyContains(otherRange: IntRange): Boolean = this.contains(otherRange.first) || this.contains(otherRange.last)
    fun Pair<IntRange, IntRange>.fullyContains(): Boolean {
        return this.first.fullyContains(this.second) || this.second.fullyContains(this.first)
    }
    fun Pair<IntRange, IntRange>.partiallyContains(): Boolean {
        return this.first.partiallyContains(this.second) || this.second.partiallyContains(this.first)
    }
    fun calculateSectionAssignments(input: String): Pair<IntRange, IntRange> {
        val split = input.split(",")
        val firstAssignment = IntRange.fromList(split[0].split("-").map { it.toInt() })
        val secondAssignment = IntRange.fromList(split[1].split("-").map { it.toInt() })
        return Pair(firstAssignment, secondAssignment)
    }
    fun partOne(input: List<String>): Int {
        return input.map { calculateSectionAssignments(it) }.count { it.fullyContains() }
    }

    fun partTwo(input: List<String>): Int {
        return input.map { calculateSectionAssignments(it) }.count { it.partiallyContains() }
    }

    private val input = FileIO.readInput("day04input.txt") { s -> s }

    @JvmStatic
    fun main(args: Array<String>) {
        val partOneSolution = partOne(input)
        println(partOneSolution)

        val partTwoSolution = partTwo(input)
        println(partTwoSolution)
    }
}