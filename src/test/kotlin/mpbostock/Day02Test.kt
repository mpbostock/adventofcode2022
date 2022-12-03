package mpbostock

import mpbostock.Day02.HandShape
import mpbostock.Day02.Game
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day02Test {

    val testInput = listOf(
        "A Y",
        "B X",
        "C Z"
    )

    @Test
    fun `Rock from opponent char A`() {
        assertEquals(HandShape.ROCK, HandShape.fromOpponentChar('A'))
    }

    @Test
    fun `Rock from player char X`() {
        assertEquals(HandShape.ROCK, HandShape.fromPlayerChar('X'))
    }

    @Test
    fun `Paper from opponent char B`() {
        assertEquals(HandShape.PAPER, HandShape.fromOpponentChar('B'))
    }

    @Test
    fun `Paper from player char Y`() {
        assertEquals(HandShape.PAPER, HandShape.fromPlayerChar('Y'))
    }

    @Test
    fun `Scissors from opponent char C`() {
        assertEquals(HandShape.SCISSORS, HandShape.fromOpponentChar('C'))
    }

    @Test
    fun `Scissors from player char Z`() {
        assertEquals(HandShape.SCISSORS, HandShape.fromPlayerChar('Z'))
    }

    @Test
    fun `Rock beats Scissors`() {
        assertTrue(HandShape.ROCK.beats(HandShape.SCISSORS))
    }

    @Test
    fun `Paper beats Rock`() {
        assertTrue(HandShape.PAPER.beats(HandShape.ROCK))
    }

    @Test
    fun `Scissors beats Paper`() {
        assertTrue(HandShape.SCISSORS.beats(HandShape.PAPER))
    }

    @Test
    fun `Rock does not beat Paper`() {
        assertFalse(HandShape.ROCK.beats(HandShape.PAPER))
    }

    @Test
    fun `Paper does not beat Scissors`() {
        assertFalse(HandShape.PAPER.beats(HandShape.SCISSORS))
    }

    @Test
    fun `Scissors does not beat Rock`() {
        assertFalse(HandShape.SCISSORS.beats(HandShape.ROCK))
    }

    @Test
    fun `A lost game returns just the player's hand score`() {
        val game1 = Game(HandShape.PAPER, HandShape.ROCK)
        assertEquals(1, game1.score())
        val game2 = Game(HandShape.SCISSORS, HandShape.PAPER)
        assertEquals(2, game2.score())
        val game3 = Game(HandShape.ROCK, HandShape.SCISSORS)
        assertEquals(3, game3.score())
    }

    @Test
    fun `A drawn game returns 3 plus player's hand score`() {
        val game1 = Game(HandShape.ROCK, HandShape.ROCK)
        assertEquals(4, game1.score())
        val game2 = Game(HandShape.PAPER, HandShape.PAPER)
        assertEquals(5, game2.score())
        val game3 = Game(HandShape.SCISSORS, HandShape.SCISSORS)
        assertEquals(6, game3.score())
    }

    @Test
    fun `A won game returns 6 plus player's hand score`() {
        val game1 = Game(HandShape.SCISSORS, HandShape.ROCK)
        assertEquals(7, game1.score())
        val game2 = Game(HandShape.ROCK, HandShape.PAPER)
        assertEquals(8, game2.score())
        val game3 = Game(HandShape.PAPER, HandShape.SCISSORS)
        assertEquals(9, game3.score())
    }

    @Test
    fun `A Game is read from a string`() {
        val game1 = Game.fromString(testInput[0])
        assertEquals(HandShape.ROCK, game1.opponent)
        assertEquals(HandShape.PAPER, game1.player)
        val game2 = Game.fromString(testInput[1])
        assertEquals(HandShape.PAPER, game2.opponent)
        assertEquals(HandShape.ROCK, game2.player)
        val game3 = Game.fromString(testInput[2])
        assertEquals(HandShape.SCISSORS, game3.opponent)
        assertEquals(HandShape.SCISSORS, game3.player)
    }

    @Test
    fun `A Game is read from a win, lose or draw string`() {
        val game1 = Game.fromStringWinLoseOrDraw(testInput[0])
        assertEquals(HandShape.ROCK, game1.opponent)
        assertEquals(HandShape.ROCK, game1.player)
        val game2 = Game.fromStringWinLoseOrDraw(testInput[1])
        assertEquals(HandShape.PAPER, game2.opponent)
        assertEquals(HandShape.ROCK, game2.player)
        val game3 = Game.fromStringWinLoseOrDraw(testInput[2])
        assertEquals(HandShape.SCISSORS, game3.opponent)
        assertEquals(HandShape.ROCK, game3.player)
    }

    @Test
    fun `Total score for test data`() {
        assertEquals(15, testInput.map { Game.fromString(it) }.sumOf { it.score() })
    }

    @Test
    fun `Total score for win, lose or draw test data`() {
        assertEquals(12, testInput.map { Game.fromStringWinLoseOrDraw(it) }.sumOf { it.score() })
    }

}