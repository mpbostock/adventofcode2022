package mpbostock

object Day06 {
    fun findStartingPacketIndex(datastream: String, uniqueSize: Int): Int {
        val firstUnique =
            datastream.windowed(uniqueSize).map { it.toSet() }.first { it.size == uniqueSize }.joinToString("")
        return firstUnique.let { datastream.indexOf(it) + uniqueSize }
    }

    fun partOne(input: List<String>): Int {
        return findStartingPacketIndex(input[0], 4)
    }

    fun partTwo(input: List<String>): Int {
        return findStartingPacketIndex(input[0], 14)
    }

    private val input = FileIO.readInput("day06input.txt") { s -> s }

    @JvmStatic
    fun main(args: Array<String>) {
        val partOneSolution = partOne(input)
        println(partOneSolution)

        val partTwoSolution = partTwo(input)
        println(partTwoSolution)
    }
}