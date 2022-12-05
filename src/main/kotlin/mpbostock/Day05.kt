package mpbostock

object Day05 {
    fun interface RearrangementCommand {
        fun rearrange(numCrates: Int, fromStack: ArrayDeque<Char>, toStack: ArrayDeque<Char>)
    }

    object CrateMover9000Command: RearrangementCommand {
        override fun rearrange(numCrates: Int, fromStack: ArrayDeque<Char>, toStack: ArrayDeque<Char>) {
            for (i in 0 until numCrates) {
                toStack.addFirst(fromStack.removeFirst())
            }
        }
    }

    object CrateMover9001Command: RearrangementCommand {
        override fun rearrange(numCrates: Int, fromStack: ArrayDeque<Char>, toStack: ArrayDeque<Char>) {
            for (i in numCrates - 1 downTo 0) {
                toStack.addFirst(fromStack.removeAt(i))
            }
        }
    }

    data class RearrangementStep(val number: Int, val fromId: Int, val toId: Int) {
        companion object {
            val lineRegex = """move (\d+) from (\d+) to (\d+)""".toRegex()
            fun fromRearrangementRow(row: String): RearrangementStep {
                val (num, from, to) = lineRegex.find(row)!!.destructured
                return RearrangementStep(num.toInt(), from.toInt(), to.toInt())
            }
        }
    }

    class GiantCargoCrane(
        private val stacks: Map<Int, ArrayDeque<Char>>,
        private val rearrangementProcedure: List<RearrangementStep>,
        private val rearrangementCommand: RearrangementCommand
    ) {
        fun rearrangeCrates() {
            for (rearrangementStep in rearrangementProcedure) {
                val fromStack = stacks[rearrangementStep.fromId]!!
                val toStack = stacks[rearrangementStep.toId]!!
                rearrangementCommand.rearrange(rearrangementStep.number, fromStack, toStack)
            }
        }

        fun topCrates(): String {
            return stacks.map { it.value.first() }.joinToString("")
        }

        companion object {
            fun buildCrane(input: List<String>, rearrangementCommand: RearrangementCommand): GiantCargoCrane {
                val (stacks, rearrangement) = getStacksAndRearrangement(input)
                return GiantCargoCrane(parseStacks(stacks), parseRearrangementProcedure(rearrangement), rearrangementCommand)
            }
        }
    }

    fun getStacksAndRearrangement(input: List<String>): Pair<List<String>, List<String>> {
        return Pair(input.takeWhile { it.isNotEmpty() }, input.takeLastWhile { it.isNotEmpty() })
    }

    fun parseStacks(input: List<String>): Map<Int, ArrayDeque<Char>> {
        val length = input.maxOf { it.length }
        val idRow = input.takeLast(1).first()
        val crateRows = input.dropLast(1)
        val stackIndices = (1..length step 4)
        val stackIds = stackIndices.map { idRow[it].digitToInt() }
        val stackDeques = stackIds.map { it to ArrayDeque<Char>() }.toMap()

        for (crateRow in crateRows) {
            for ((index, id) in stackIndices.zip(stackIds)) {
                if (index < crateRow.length) {
                    crateRow[index].takeUnless { it == ' ' }?.run { stackDeques[id]?.addLast(this) }
                }
            }
        }
        return stackDeques
    }

    fun parseRearrangementProcedure(input: List<String>): List<RearrangementStep> {
        return input.map { RearrangementStep.fromRearrangementRow(it) }
    }

    fun partOne(input: List<String>): String {
        val crane = GiantCargoCrane.buildCrane(input, CrateMover9000Command)
        crane.rearrangeCrates()
        return crane.topCrates()
    }

    fun partTwo(input: List<String>): String {
        val crane = GiantCargoCrane.buildCrane(input, CrateMover9001Command)
        crane.rearrangeCrates()
        return crane.topCrates()
    }

    private val input = FileIO.readInput("day05input.txt") { s -> s }

    @JvmStatic
    fun main(args: Array<String>) {
        val partOneSolution = partOne(input)
        println(partOneSolution)

        val partTwoSolution = partTwo(input)
        println(partTwoSolution)
    }
}