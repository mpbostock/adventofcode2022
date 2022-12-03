package mpbostock

import mpbostock.Day03.Items
import mpbostock.Day03.chopInHalf
import mpbostock.Day03.commonChar
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day03Test {
    val testData = listOf(
        "vJrwpWtwJgWrhcsFMMfFFhFp",
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
        "PmmdzqPrVvPwwTWBwg",
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
        "ttgJtRGJQctTZtZT",
        "CrZsJsPPZsGzwwsLwLmpwMDw",
    )

    @Test
    fun `lowercase priorities range from 1-26`() {
        for ((char, priority) in ('a'..'z').zip(1..26)) {
            assertEquals(priority, Items.priorityFor(char))
        }
    }

    @Test
    fun `uppercase priorities range from 27-52`() {
        for ((char, priority) in ('A'..'Z').zip(27..52)) {
            assertEquals(priority, Items.priorityFor(char))
        }
    }

    @Test
    fun `rucksack splits in half`() {
        val twoHalves = testData[0].chopInHalf()
        assertEquals("vJrwpWtwJgWr", twoHalves[0])
        assertEquals("hcsFMMfFFhFp", twoHalves[1])
    }

    @Test
    fun `common item is in both compartments of rucksack`() {
        val twoHalves = testData[0].chopInHalf()
        assertEquals('p', twoHalves.commonChar())
    }

    @Test
    fun `sum of priorities in test data`() {
        assertEquals(157, Day03.partOne(testData))
    }

    @Test
    fun `common item is in rucksack group`() {
        val rucksackGroup = listOf(testData[0], testData[1], testData[2])
        assertEquals('r', rucksackGroup.commonChar())
    }

    @Test
    fun `sum of rucksack group priorities in test data`() {
        assertEquals(70, Day03.partTwo(testData))
    }

}