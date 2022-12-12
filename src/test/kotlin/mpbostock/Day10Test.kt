package mpbostock

import mpbostock.Day10.ClockCircuit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day10Test {
    val testInput = listOf(
        "noop",
        "addx 3",
        "addx -5",
    )

    val extendedTestInput = listOf(
        "addx 15",
        "addx -11",
        "addx 6",
        "addx -3",
        "addx 5",
        "addx -1",
        "addx -8",
        "addx 13",
        "addx 4",
        "noop",
        "addx -1",
        "addx 5",
        "addx -1",
        "addx 5",
        "addx -1",
        "addx 5",
        "addx -1",
        "addx 5",
        "addx -1",
        "addx -35",
        "addx 1",
        "addx 24",
        "addx -19",
        "addx 1",
        "addx 16",
        "addx -11",
        "noop",
        "noop",
        "addx 21",
        "addx -15",
        "noop",
        "noop",
        "addx -3",
        "addx 9",
        "addx 1",
        "addx -3",
        "addx 8",
        "addx 1",
        "addx 5",
        "noop",
        "noop",
        "noop",
        "noop",
        "noop",
        "addx -36",
        "noop",
        "addx 1",
        "addx 7",
        "noop",
        "noop",
        "noop",
        "addx 2",
        "addx 6",
        "noop",
        "noop",
        "noop",
        "noop",
        "noop",
        "addx 1",
        "noop",
        "noop",
        "addx 7",
        "addx 1",
        "noop",
        "addx -13",
        "addx 13",
        "addx 7",
        "noop",
        "addx 1",
        "addx -33",
        "noop",
        "noop",
        "noop",
        "addx 2",
        "noop",
        "noop",
        "noop",
        "addx 8",
        "noop",
        "addx -1",
        "addx 2",
        "addx 1",
        "noop",
        "addx 17",
        "addx -9",
        "addx 1",
        "addx 1",
        "addx -3",
        "addx 11",
        "noop",
        "noop",
        "addx 1",
        "noop",
        "addx 1",
        "noop",
        "noop",
        "addx -13",
        "addx -19",
        "addx 1",
        "addx 3",
        "addx 26",
        "addx -30",
        "addx 12",
        "addx -1",
        "addx 3",
        "addx 1",
        "noop",
        "noop",
        "noop",
        "addx -9",
        "addx 18",
        "addx 1",
        "addx 2",
        "noop",
        "noop",
        "addx 9",
        "noop",
        "noop",
        "noop",
        "addx -1",
        "addx 2",
        "addx -37",
        "addx 1",
        "addx 3",
        "noop",
        "addx 15",
        "addx -21",
        "addx 22",
        "addx -6",
        "addx 1",
        "noop",
        "addx 2",
        "addx 1",
        "noop",
        "addx -10",
        "noop",
        "noop",
        "addx 20",
        "addx 1",
        "addx 2",
        "addx 2",
        "addx -6",
        "addx -11",
        "noop",
        "noop",
        "noop",
    )

    @Test
    fun `X register is correct for cycle num`() {
        val clockCircuit = ClockCircuit()
        clockCircuit.execute(testInput)
        assertEquals(1, clockCircuit.xRegister(1))
        assertEquals(1, clockCircuit.xRegister(2))
        assertEquals(1, clockCircuit.xRegister(3))
        assertEquals(4, clockCircuit.xRegister(4))
        assertEquals(4, clockCircuit.xRegister(5))
        assertEquals(-1, clockCircuit.xRegister(6))
    }

    @Test
    fun `X register values are correct for extended test input`() {
        val clockCircuit = ClockCircuit()
        clockCircuit.execute(extendedTestInput)
        assertEquals(21, clockCircuit.xRegister(20))
        assertEquals(19, clockCircuit.xRegister(60))
        assertEquals(18, clockCircuit.xRegister(100))
        assertEquals(21, clockCircuit.xRegister(140))
        assertEquals(16, clockCircuit.xRegister(180))
        assertEquals(18, clockCircuit.xRegister(220))
    }

    @Test
    fun `the interesting signal strengths are correct for extended test input`() {
        val clockCircuit = ClockCircuit()
        clockCircuit.execute(extendedTestInput)
        assertEquals(420, clockCircuit.signalStrength(20))
        assertEquals(1140, clockCircuit.signalStrength(60))
        assertEquals(1800, clockCircuit.signalStrength(100))
        assertEquals(2940, clockCircuit.signalStrength(140))
        assertEquals(2880, clockCircuit.signalStrength(180))
        assertEquals(3960, clockCircuit.signalStrength(220))
    }


    @Test
    fun `CRT display for extended test input`() {
        val clockCircuit = ClockCircuit()
        clockCircuit.execute(extendedTestInput)
        val crtDisplay = clockCircuit.crtDisplay()
        assertEquals("##..##..##..##..##..##..##..##..##..##..", crtDisplay[0])
        assertEquals("###...###...###...###...###...###...###.", crtDisplay[1])
        assertEquals("####....####....####....####....####....", crtDisplay[2])
        assertEquals("#####.....#####.....#####.....#####.....", crtDisplay[3])
        assertEquals("######......######......######......####", crtDisplay[4])
        assertEquals("#######.......#######.......#######.....", crtDisplay[5])
    }

    @Test
    fun `test part one with extended test data`() {
        assertEquals(13140, Day10.partOne(extendedTestInput))
    }

}