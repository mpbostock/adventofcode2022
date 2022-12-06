package mpbostock

import mpbostock.Day06.findStartingPacketIndex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day06Test {
    val testInput = "mjqjpqmgbljsphdztnvjfqwrcgsmlb"
    @Test
    fun `first set of 4 unique chars found before index 7`() {
        assertEquals(7, findStartingPacketIndex(testInput, 4))
    }

    @Test
    fun `first set of 14 uniwue chars found before index 19`() {
        assertEquals(19, findStartingPacketIndex(testInput, 14))
    }

}