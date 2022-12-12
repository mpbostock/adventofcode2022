package mpbostock

object Day10 {
    class ClockCircuit(initialX: Int = 1) {
        private val cycles = arrayListOf(initialX)
        private var xRegister = initialX
        fun execute(instructions: List<String>) {
            instructions.forEach {
                when (it) {
                    "noop" -> addCurrentXRegister()
                    else -> {
                        val split = it.split(' ')
                        addCurrentXRegister()
                        xRegister += split[1].toInt()
                        addCurrentXRegister()
                    }
                }
            }
        }

        fun xRegister(cycleNum: Int) = cycles[cycleNum - 1]

        fun signalStrength(cycleNum: Int) = cycleNum * xRegister(cycleNum)

        fun crtDisplay(): List<String> = cycles.windowed(40, 40).map { it.mapIndexed(::crtPixel).joinToString("") }

        private fun crtPixel(index: Int, xRegister: Int): Char = '#'.takeIf { pixelInSprite(index, xRegister) } ?: '.'

        private fun pixelInSprite(index: Int, xRegister: Int) = index in (xRegister - 1)..(xRegister + 1)

        private fun addCurrentXRegister() {
            cycles.add(xRegister)
        }
    }

    fun partOne(input: List<String>): Int {
        val clockCircuit = ClockCircuit()
        clockCircuit.execute(input)
        return (20..220 step 40).sumOf { clockCircuit.signalStrength(it) }
    }

    fun partTwo(input: List<String>): List<String> {
        val clockCircuit = ClockCircuit()
        clockCircuit.execute(input)
        return clockCircuit.crtDisplay()
    }

    private val input = FileIO.readInput("day10input.txt") { s -> s }

    @JvmStatic
    fun main(args: Array<String>) {
        val partOneSolution = partOne(input)
        println(partOneSolution)

        val partTwoSolution = partTwo(input)
        partTwoSolution.forEach { println(it) }
    }
}