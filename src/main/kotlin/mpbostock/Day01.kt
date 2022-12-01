package mpbostock

object Day01 {
    fun partOne(input: List<String>): Int {
        val elves = Elves.fromInput(input)
        return elves.mostCalories()
    }

    fun partTwo(input: List<String>): Int {
        val elves = Elves.fromInput(input)
        return elves.mostCaloriesTopThree()
    }

    class Elf(private val food: List<Int>) {
        fun totalCalories(): Int = food.sum()
    }

    class Elves(private val elves: List<Elf>) {
        fun mostCalories(): Int = elves.maxOfOrNull { it.totalCalories() } ?: 0
        fun mostCaloriesTopThree(): Int = elves.sortedByDescending { it.totalCalories() }.take(3).sumOf { it.totalCalories() }
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