package mpbostock

import mpbostock.Day09.Direction
import mpbostock.Day09.Move
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day09Test {
    val testInput = listOf(
        "R 4",
        "U 4",
        "L 3",
        "D 1",
        "R 4",
        "D 1",
        "L 5",
        "R 2",
    )

    val testLargerInput = listOf(
        "R 5",
        "U 8",
        "L 8",
        "D 3",
        "R 17",
        "D 10",
        "L 25",
        "U 20",
    )

    @Test
    fun `input gets converted to move up`() {
        assertEquals(Move(Direction.UP, 4), Move.fromInput(testInput[1]))
    }

    @Test
    fun `input gets converted to move down`() {
        assertEquals(Move(Direction.DOWN, 1), Move.fromInput(testInput[3]))
    }

    @Test
    fun `input gets converted to move left`() {
        assertEquals(Move(Direction.LEFT, 3), Move.fromInput(testInput[2]))
    }

    @Test
    fun `input gets converted to move right`() {
        assertEquals(Move(Direction.RIGHT, 4), Move.fromInput(testInput[0]))
    }

    @Test
    fun `head moves right 4`() {
        val ropeBridge = Day09.RopeBridge()
        val move = Move(Direction.RIGHT, 4)
        ropeBridge.move(move)
        assertEquals(Vector2d(4, 0), ropeBridge.head)
    }

    @Test
    fun `head moves up 4`() {
        val ropeBridge = Day09.RopeBridge()
        val move = Move(Direction.UP, 4)
        ropeBridge.move(move)
        assertEquals(Vector2d(0, -4), ropeBridge.head)
    }

    @Test
    fun `head moves left 3`() {
        val ropeBridge = Day09.RopeBridge()
        val move = Move(Direction.LEFT, 3)
        ropeBridge.move(move)
        assertEquals(Vector2d(-3, 0), ropeBridge.head)
    }

    @Test
    fun `head moves down 1`() {
        val ropeBridge = Day09.RopeBridge()
        val move = Move(Direction.DOWN, 1)
        ropeBridge.move(move)
        assertEquals(Vector2d(0, 1), ropeBridge.head)
    }

    @Test
    fun `tail moves left to follow head`() {
        val ropeBridge = Day09.RopeBridge()
        val move = Move(Direction.LEFT, 2)
        ropeBridge.move(move)
        assertEquals(Vector2d(-1, 0), ropeBridge.tail)
    }

    @Test
    fun `tail moves right to follow head`() {
        val ropeBridge = Day09.RopeBridge()
        val move = Move(Direction.RIGHT, 2)
        ropeBridge.move(move)
        assertEquals(Vector2d(1, 0), ropeBridge.tail)
    }

    @Test
    fun `tail moves up to follow head`() {
        val ropeBridge = Day09.RopeBridge()
        val move = Move(Direction.UP, 2)
        ropeBridge.move(move)
        assertEquals(Vector2d(0, -1), ropeBridge.tail)
    }

    @Test
    fun `tail moves down to follow head`() {
        val ropeBridge = Day09.RopeBridge()
        val move = Move(Direction.DOWN, 2)
        ropeBridge.move(move)
        assertEquals(Vector2d(0, 1), ropeBridge.tail)
    }

    @Test
    fun `tail moves up to right diagonally to follow head`() {
        val ropeBridge = Day09.RopeBridge()
        val right = Move(Direction.RIGHT, 1)
        val up = Move(Direction.UP, 2)
        ropeBridge.move(right)
        ropeBridge.move(up)
        assertEquals(Vector2d(1, -1), ropeBridge.tail)
    }

    @Test
    fun `tail moves up to left diagonally to follow head`() {
        val ropeBridge = Day09.RopeBridge()
        val left = Move(Direction.LEFT, 1)
        val up = Move(Direction.UP, 2)
        ropeBridge.move(left)
        ropeBridge.move(up)
        assertEquals(Vector2d(-1, -1), ropeBridge.tail)
    }

    @Test
    fun `tail moves down to right diagonally to follow head`() {
        val ropeBridge = Day09.RopeBridge()
        val right = Move(Direction.RIGHT, 1)
        val down = Move(Direction.DOWN, 2)
        ropeBridge.move(right)
        ropeBridge.move(down)
        assertEquals(Vector2d(1, 1), ropeBridge.tail)
    }

    @Test
    fun `tail moves down to left diagonally to follow head`() {
        val ropeBridge = Day09.RopeBridge()
        val left = Move(Direction.LEFT, 1)
        val down = Move(Direction.DOWN, 2)
        ropeBridge.move(left)
        ropeBridge.move(down)
        assertEquals(Vector2d(-1, 1), ropeBridge.tail)
    }

    @Test
    fun `part one for test input`() {
        val partOne = Day09.partOne(testInput.map { Move.fromInput(it) })
        assertEquals(13, partOne)
    }

    @Test
    fun `part two for test input`() {
        val partTwo = Day09.partTwo(testInput.map { Move.fromInput(it) })
        assertEquals(1, partTwo)
    }

    @Test
    fun `part two for larger test input`() {
        val partTwo = Day09.partTwo(testLargerInput.map { Move.fromInput(it) })
        assertEquals(36, partTwo)
    }

}