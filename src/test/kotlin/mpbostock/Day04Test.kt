package mpbostock

import mpbostock.Day04.fullyContains
import mpbostock.Day04.partiallyContains
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day04Test {
    val testInput = listOf(
        "2-4,6-8",
        "2-3,4-5",
        "5-7,7-9",
        "2-8,3-7",
        "6-6,4-6",
        "2-6,4-8",
    )

    @Test
    fun `String input is converted to two Assignments`() {
        val sectionAssignmentPair = Day04.calculateSectionAssignments(testInput[0])
        assertEquals(2, sectionAssignmentPair.first.first)
        assertEquals(4, sectionAssignmentPair.first.last)
        assertEquals(6, sectionAssignmentPair.second.first)
        assertEquals(8, sectionAssignmentPair.second.last)
    }

    @Test
    fun `Two assignments don't fully contain one or the other`() {
        assertFalse(Day04.calculateSectionAssignments(testInput[0]).fullyContains())
        assertFalse(Day04.calculateSectionAssignments(testInput[1]).fullyContains())
        assertFalse(Day04.calculateSectionAssignments(testInput[2]).fullyContains())
        assertFalse(Day04.calculateSectionAssignments(testInput[5]).fullyContains())
    }

    @Test
    fun `Two assignments fully contain one or the other`() {
        assertTrue(Day04.calculateSectionAssignments(testInput[3]).fullyContains())
        assertTrue(Day04.calculateSectionAssignments(testInput[4]).fullyContains())
    }

    @Test
    fun `Two assignments don't partially contain one or the other`() {
        assertFalse(Day04.calculateSectionAssignments(testInput[0]).partiallyContains())
        assertFalse(Day04.calculateSectionAssignments(testInput[1]).partiallyContains())
    }

    @Test
    fun `Two assignments partially contain one or the other`() {
        assertTrue(Day04.calculateSectionAssignments(testInput[2]).partiallyContains())
        assertTrue(Day04.calculateSectionAssignments(testInput[3]).partiallyContains())
        assertTrue(Day04.calculateSectionAssignments(testInput[4]).partiallyContains())
        assertTrue(Day04.calculateSectionAssignments(testInput[5]).partiallyContains())
    }

    @Test
    fun `Total paired assignments fully contained for test input`() {
        assertEquals(2, Day04.partOne(testInput))
    }

    @Test
    fun `Total paired assignments partially contained for test input`() {
        assertEquals(4, Day04.partTwo(testInput))
    }

}