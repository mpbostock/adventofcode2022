package mpbostock

import kotlin.math.abs

object Day09 {
    enum class Direction(val changeVector: Vector2d) {
        UP(Vector2d(0, -1)),
        DOWN(Vector2d(0, 1)),
        LEFT(Vector2d(-1, 0)),
        RIGHT(Vector2d(1, 0));
    }
    data class Move(val direction: Direction, val amount: Int) {
        companion object {
            fun fromInput(input: String): Move {
                val split = input.split(' ')
                val amount = split[1].toInt()
                return when (split[0]) {
                    "U" -> Move(Direction.UP, amount)
                    "D" -> Move(Direction.DOWN, amount)
                    "L" -> Move(Direction.LEFT, amount)
                    else -> Move(Direction.RIGHT, amount)
                }
            }
        }
    }
    class RopeBridge(startingPos: Vector2d = Vector2d.origin, numKnots: Int = 2) {
        private var knots = (1..numKnots).map { startingPos }.toTypedArray()
        private val lastIndex = knots.indices.last
        val head: Vector2d
            get() = knots.first()
        val tail: Vector2d
            get() = knots.last()
        private val tailPositions = mutableSetOf(knots.last())
        fun move(move: Move) {
            for (step in (1..move.amount)) {
                moveHead(move.direction)
                for (i in 1..lastIndex) {
                    moveKnot(i)
                    if (i == lastIndex) tailPositions.add(tail)
                }
            }
        }

        private fun moveKnot(i: Int) {
            val currentKnot = knots[i]
            val previousKnot = knots[i - 1]
            val diffVector = previousKnot - currentKnot
            if (knotShouldMove(currentKnot, previousKnot)) {
                val changeVector = diffVector.sign
                knots[i] += changeVector
            }
        }

        private fun moveHead(direction: Direction): Vector2d {
            val oldPos = knots[0]
            knots[0] += direction.changeVector
            return oldPos
        }

        fun simulate(moves: List<Move>): Int {
            moves.forEach { move(it) }
            return tailPositions.size
        }

        private fun knotShouldMove(knot: Vector2d, previousKnot: Vector2d): Boolean {
            return (abs(knot.x - previousKnot.x) > 1 || abs(knot.y - previousKnot.y) > 1)
        }
    }
    fun partOne(input: List<Move>): Int {
        return RopeBridge().simulate(input)
    }

    fun partTwo(input: List<Move>): Int {
        return RopeBridge(numKnots = 10).simulate(input)
    }

    private val input = FileIO.readInput("day09input.txt") { s -> Move.fromInput(s) }

    @JvmStatic
    fun main(args: Array<String>) {
        val partOneSolution = partOne(input)
        println(partOneSolution)

        val partTwoSolution = partTwo(input)
        println(partTwoSolution)
    }
}