package mpbostock

object Day08 {
    class Forest(private val treeHeights: Array<IntArray>) {
        private val width = treeHeights[0].size
        private val height = treeHeights.size

        fun clownsToTheLeftOf(coord: Vector2d) = (0 until coord.x).map { Vector2d(it, coord.y) }
        fun jokersToTheRight(coord: Vector2d) = (coord.x + 1 until width).map { Vector2d(it, coord.y) }
        fun treesAbove(coord: Vector2d) = (0 until coord.y).map { Vector2d(coord.x, it) }
        fun treesBelow(coord: Vector2d) = (coord.y + 1 until height).map { Vector2d(coord.x, it) }

        fun treeIsVisible(coord: Vector2d): Boolean {
            return isEdgeOfForest(coord) || canSeeWoodForTheTrees(coord)
        }

        fun viewingDistance(coord: Vector2d, otherTrees: List<Vector2d>): Int {
            if (otherTrees.isEmpty()) return 0
            val treeHeight = getHeight(coord)
            val otherTreeCoordsHeights = otherTrees.zip(otherTrees.map { getHeight(it) })
            return coord.abs(otherTreeCoordsHeights.firstOrNull { it.second >= treeHeight }?.first ?: otherTrees.last())
        }

        fun scenicScore(tree: Vector2d): Int {
            val distanceAbove = viewingDistance(tree, treesAbove(tree).reversed())
            val distanceLeft = viewingDistance(tree, clownsToTheLeftOf(tree).reversed())
            val distanceRight = viewingDistance(tree, jokersToTheRight(tree))
            val distanceBelow = viewingDistance(tree, treesBelow(tree))
            return distanceAbove * distanceLeft * distanceRight * distanceBelow
        }

        private fun isEdgeOfForest(coord: Vector2d): Boolean {
            return coord.x == 0 || coord.x == width - 1 || coord.y == 0 || coord.y == height - 1
        }

        private fun canSeeWoodForTheTrees(coord: Vector2d): Boolean {
            val treeHeight = getHeight(coord)
            val heightsToTheLeft = getHeights(clownsToTheLeftOf(coord))
            val heightsToTheRight = getHeights(jokersToTheRight(coord))
            val heightsAbove = getHeights(treesAbove(coord))
            val heightsBelow = getHeights(treesBelow(coord))
            return heightsToTheLeft.all { it < treeHeight } || heightsToTheRight.all { it < treeHeight } || heightsAbove.all { it < treeHeight } || heightsBelow.all { it < treeHeight }
        }

        private fun allCoords(): List<Vector2d> {
            val coords = emptyList<Vector2d>().toMutableList()
            for (x in 0 until width) {
                for (y in 0 until height) {
                    coords.add(Vector2d(x, y))
                }
            }
            return coords
        }

        private fun getHeight(coord: Vector2d) = treeHeights[coord.y][coord.x]
        private fun getHeights(coords: List<Vector2d>) = coords.map { getHeight(it) }
        fun numVisibleTrees(): Int {
            return allCoords().map { treeIsVisible(it) }.count { it }
        }

        fun bestScenicScore(): Int {
            return allCoords().map { scenicScore(it) }.maxOf { it }
        }

        companion object {
            fun read(input: List<String>): Forest {
                return Forest(input.map { it.map(Char::digitToInt).toIntArray() }.toTypedArray())
            }
        }
    }

    fun partOne(input: List<String>): Int {
        return Forest.read(input).numVisibleTrees()
    }

    fun partTwo(input: List<String>): Int {
        return Forest.read(input).bestScenicScore()
    }

    private val input = FileIO.readInput("day08input.txt") { s -> s }

    @JvmStatic
    fun main(args: Array<String>) {
        val partOneSolution = partOne(input)
        println(partOneSolution)

        val partTwoSolution = partTwo(input)
        println(partTwoSolution)
    }
}