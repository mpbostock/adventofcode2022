package mpbostock

object Day03 {
    object Items {
        private val lowercasePriorities = ('a'..'z').zip(1..26).toMap()
        private val uppercasePriorities = ('A'..'Z').zip(27..52).toMap()
        private val allItemPriorities = lowercasePriorities + uppercasePriorities
        fun priorityFor(item: Char): Int = allItemPriorities[item]!!
    }

    class Rucksack(allItems: String) {
        val firstCompartment = allItems.substring(0, allItems.length / 2)
        val secondCompartment = allItems.substring(allItems.length / 2, allItems.length)
        val commonItem = firstCompartment.toSet().intersect(secondCompartment.toSet()).first()
    }

    class RucksackGroup(rucksacks: Triple<String, String, String>) {
        val commonItem = rucksacks.first.toSet().intersect(rucksacks.second.toSet()).intersect(rucksacks.third.toSet()).first()
    }

    fun partOne(input: List<String>): Int {
        return input.sumOf { Items.priorityFor(Rucksack(it).commonItem) }
    }

    fun partTwo(input: List<String>): Int {
        return input.windowed(3, 3).sumOf { Items.priorityFor(RucksackGroup(Triple(it[0], it[1], it[2])).commonItem) }
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