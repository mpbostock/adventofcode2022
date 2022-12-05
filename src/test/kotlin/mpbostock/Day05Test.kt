package mpbostock

import mpbostock.Day05.CrateMover9000Command
import mpbostock.Day05.CrateMover9001Command
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

import mpbostock.Day05.getStacksAndRearrangement
import mpbostock.Day05.parseRearrangementProcedure
import mpbostock.Day05.parseStacks

internal class Day05Test {
    val testInput = listOf(
        "    [D]    ",
        "[N] [C]    ",
        "[Z] [M] [P]",
        " 1   2   3 ",
        "",
        "move 1 from 2 to 1",
        "move 3 from 1 to 3",
        "move 2 from 2 to 1",
        "move 1 from 1 to 2",
        )

    @Test
    fun `input is split into stacks and rearrangement procedure`() {
        val (stacks, rearrangement) = getStacksAndRearrangement(testInput)
        assertEquals(4, stacks.size)
        assertEquals(testInput[0], stacks[0])
        assertEquals(testInput[1], stacks[1])
        assertEquals(testInput[2], stacks[2])
        assertEquals(testInput[3], stacks[3])
        assertEquals(4, rearrangement.size)
        assertEquals(testInput[5], rearrangement[0])
        assertEquals(testInput[6], rearrangement[1])
        assertEquals(testInput[7], rearrangement[2])
        assertEquals(testInput[8], rearrangement[3])
    }

    @Test
    fun `stacks are parsed from first part of input`() {
        val (stacks, _) = getStacksAndRearrangement(testInput)
        val stackDeques = parseStacks(stacks)
        assertEquals(3, stackDeques.size)
        val pos1Stack = stackDeques[1]
        pos1Stack?.let {
            assertEquals(2, it.size)
            assertEquals('N', it[0])
            assertEquals('Z', it[1])
        }
        val pos2Stack = stackDeques[2]
        pos2Stack?.let {
            assertEquals(3, it.size)
            assertEquals('D', it[0])
            assertEquals('C', it[1])
            assertEquals('M', it[2])
        }
        val pos3Stack = stackDeques[3]
        pos3Stack?.let {
            assertEquals(1, it.size)
            assertEquals('P', it[0])
        }
    }

    @Test
    fun `rearrangement steps are parsed from second part of input`() {
        val (_, rearrangement) = getStacksAndRearrangement(testInput)
        val rearrangementSteps = parseRearrangementProcedure(rearrangement)
        assertEquals(4, rearrangementSteps.size)
        assertEquals(Day05.RearrangementStep(1, 2, 1), rearrangementSteps[0])
        assertEquals(Day05.RearrangementStep(3, 1, 3), rearrangementSteps[1])
        assertEquals(Day05.RearrangementStep(2, 2, 1), rearrangementSteps[2])
        assertEquals(Day05.RearrangementStep(1, 1, 2), rearrangementSteps[3])
    }

    @Test
    fun `CrateMover9000Command moves one crate at a time`() {
        val (stacks, _) = getStacksAndRearrangement(testInput)
        val stackDeques = parseStacks(stacks)
        CrateMover9000Command.rearrange(1, stackDeques[2]!!, stackDeques[1]!!)
        stackDeques[1]?.let {
            assertEquals("[D, N, Z]", it.toString())
        }
        stackDeques[2]?.let {
            assertEquals("[C, M]", it.toString())
        }
        CrateMover9000Command.rearrange(3, stackDeques[1]!!, stackDeques[3]!!)
        stackDeques[1]?.let {
            assertEquals("[]", it.toString())
        }
        stackDeques[3]?.let {
            assertEquals("[Z, N, D, P]", it.toString())
        }
        CrateMover9000Command.rearrange(2, stackDeques[2]!!, stackDeques[1]!!)
        stackDeques[1]?.let {
            assertEquals("[M, C]", it.toString())
        }
        stackDeques[2]?.let {
            assertEquals("[]", it.toString())
        }
        CrateMover9000Command.rearrange(1, stackDeques[1]!!, stackDeques[2]!!)
        stackDeques[1]?.let {
            assertEquals("[C]", it.toString())
        }
        stackDeques[2]?.let {
            assertEquals("[M]", it.toString())
        }
    }

    @Test
    fun `CrateMover9001Command moves all crates at once`() {
        val (stacks, _) = getStacksAndRearrangement(testInput)
        val stackDeques = parseStacks(stacks)
        CrateMover9001Command.rearrange(1, stackDeques[2]!!, stackDeques[1]!!)
        stackDeques[1]?.let {
            assertEquals("[D, N, Z]", it.toString())
        }
        stackDeques[2]?.let {
            assertEquals("[C, M]", it.toString())
        }
        CrateMover9001Command.rearrange(3, stackDeques[1]!!, stackDeques[3]!!)
        stackDeques[1]?.let {
            assertEquals("[]", it.toString())
        }
        stackDeques[3]?.let {
            assertEquals("[D, N, Z, P]", it.toString())
        }
        CrateMover9001Command.rearrange(2, stackDeques[2]!!, stackDeques[1]!!)
        stackDeques[1]?.let {
            assertEquals("[C, M]", it.toString())
        }
        stackDeques[2]?.let {
            assertEquals("[]", it.toString())
        }
        CrateMover9001Command.rearrange(1, stackDeques[1]!!, stackDeques[2]!!)
        stackDeques[1]?.let {
            assertEquals("[M]", it.toString())
        }
        stackDeques[2]?.let {
            assertEquals("[C]", it.toString())
        }
    }

    @Test
    fun `part one for test data`() {
        val topCrates = Day05.partOne(testInput)
        assertEquals("CMZ", topCrates)
    }

    @Test
    fun `part two for test data`() {
        val topCrates = Day05.partTwo(testInput)
        assertEquals("MCD", topCrates)
    }

}