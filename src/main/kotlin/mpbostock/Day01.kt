package mpbostock

object Day01 {
    fun partOne(input: List<String>): Int {
        return Elves.fromInput(input).highestTotalCalories()
    }

    fun partTwo(input: List<String>): Int {
        return Elves.fromInput(input).highestTotalCalories(3)
    }

    class Elf(private val food: List<Int>) {
        fun totalCalories(): Int = food.sum()
    }

    class Elves(private val elves: List<Elf>) {
        fun highestTotalCalories(numElves: Int = 1): Int =
            elves.map { it.totalCalories() }.sortedByDescending { it }.take(numElves).sum()

        companion object {
            fun fromInput(input: List<String>): Elves {
                var inputCopy = input.toList()
                val elfList = arrayListOf<Elf>()
                while (inputCopy.isNotEmpty()) {
                    val elfFood = inputCopy.takeWhile { it.isNotEmpty() }.map { it.toInt() }
                    elfList.add(Elf(elfFood))
                    inputCopy = inputCopy.drop(elfFood.size + 1)
                }
                return Elves(elfList)
            }
        }
    }

    private val input = FileIO.readInput("day01input.txt") { s -> s }

    @JvmStatic
    fun main(args: Array<String>) {
        val partOneSolution = partOne(input)
        println(partOneSolution)

        val partTwoSolution = partTwo(input)
        println(partTwoSolution)
    }
}