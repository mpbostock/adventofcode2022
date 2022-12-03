package mpbostock

import mpbostock.Day03.Items
import mpbostock.Day03.Rucksack
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
        val rucksack = Rucksack(testData[0])
        assertEquals("vJrwpWtwJgWr", rucksack.firstCompartment)
        assertEquals("hcsFMMfFFhFp", rucksack.secondCompartment)
    }

    @Test
    fun `common item is in both compartments of rucksack`() {
        val rucksack = Rucksack(testData[0])
        assertEquals('p', rucksack.commonItem)
    }

    @Test
    fun `sum of priorities in test data`() {
        val rucksacks = testData.map { Rucksack(it) }
        assertEquals(157, rucksacks.sumOf { Items.priorityFor(it.commonItem) })
    }

    @Test
    fun `common item is in rucksack group`() {
        val rucksackGroup = Day03.RucksackGroup(Triple(testData[0], testData[1], testData[2]))
        assertEquals('r', rucksackGroup.commonItem)
    }

    @Test
    fun `sum of rucksack group priorities in test data`() {
        assertEquals(70, testData.windowed(3, 3).sumOf { Items.priorityFor(
            Day03.RucksackGroup(
                Triple(
                    it[0],
                    it[1],
                    it[2]
                )
            ).commonItem) })
    }

}