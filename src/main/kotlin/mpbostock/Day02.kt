package mpbostock

object Day02 {
    enum class HandShape(val score: Int, val opponentChar: Char, val playerChar: Char) {
        ROCK(1, 'A', 'X'),
        PAPER(2, 'B', 'Y'),
        SCISSORS(3, 'C', 'Z');

        infix fun beats(otherHand: HandShape): Boolean {
            return when (this) {
                ROCK -> otherHand == SCISSORS
                PAPER -> otherHand == ROCK
                SCISSORS -> otherHand == PAPER
            }
        }

        infix fun drawsWith(otherHand: HandShape): Boolean = this == otherHand

        companion object {
            fun fromOpponentChar(char: Char) = values().single { it.opponentChar == char }
            fun fromPlayerChar(char: Char) = values().single { it.playerChar == char }
            fun toWin(opponent: HandShape): HandShape {
                return when (opponent) {
                    ROCK -> PAPER
                    PAPER -> SCISSORS
                    else -> ROCK
                }
            }
            fun toLose(opponent: HandShape): HandShape {
                return when (opponent) {
                    ROCK -> SCISSORS
                    PAPER -> ROCK
                    else -> PAPER
                }
            }
        }
    }

    class Game(val opponent: HandShape, val player: HandShape) {
        fun score(): Int {
            val result = when {
                opponent drawsWith player -> 3
                player beats opponent -> 6
                else -> 0
            }
            return result + player.score
        }
        companion object {
            fun fromString(string: String): Game {
                return Game(HandShape.fromOpponentChar(string[0]), HandShape.fromPlayerChar(string[2]))
            }
            fun fromStringWinLoseOrDraw(string: String): Game {
                val opponent = HandShape.fromOpponentChar(string[0])
                val player = when (string[2]) {
                    'X' -> HandShape.toLose(opponent)
                    'Z' -> HandShape.toWin(opponent)
                    else -> opponent
                }
                return Game(opponent, player)
            }
        }
    }

    fun partOne(input: List<String>): Int {
        return input.map { Game.fromString(it) }.sumOf { it.score() }
    }

    fun partTwo(input: List<String>): Int {
        return input.map { Game.fromStringWinLoseOrDraw(it) }.sumOf { it.score() }
    }

    private val input = FileIO.readInput("day02input.txt") { s -> s }

    @JvmStatic
    fun main(args: Array<String>) {
        val partOneSolution = partOne(input)
        println(partOneSolution)

        val partTwoSolution = partTwo(input)
        println(partTwoSolution)
    }
}