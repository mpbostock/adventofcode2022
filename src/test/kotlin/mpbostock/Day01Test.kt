package mpbostock

import mpbostock.Day01.Elf
import mpbostock.Day01.Elves
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day01Test {
    val testInput = listOf(
        "1000",
        "2000",
        "3000",
        "",
        "4000",
        "",
        "5000",
        "6000",
        "",
        "7000",
        "8000",
        "9000",
        "",
        "10000"
    )

    @Test
    fun `An elf's total calories is the sum of all the calories`() {
        val elf = Elf(listOf(100, 200, 300))
        assertEquals(600, elf.totalCalories())
    }

    @Test
    fun `The elf with the highest total calories is returned`() {
        val mostCaloriesElf = Elf(listOf(200, 300, 400))
        val leastCaloriesElf = Elf(listOf(100, 200, 300))
        val elves = Elves(listOf(leastCaloriesElf, mostCaloriesElf))
        assertEquals(900, elves.mostCalories())
    }

    @Test
    fun `No calories is returned for most calories if no elves`() {
        val elves = Elves(emptyList())
        assertEquals(0, elves.mostCalories())
    }

    @Test
    fun `Elf with most calories for test data`() {
        val elves = Elves.fromInput(testInput)
        assertEquals(24000, elves.mostCalories())
    }

    @Test
    fun `Top three elves with most calories for test data`() {
        val elves = Elves.fromInput(testInput)
        assertEquals(45000, elves.mostCaloriesTopThree())
    }
}