package mpbostock

object Day08 {
    class Forest(private val treeHeights: Array<IntArray>) {
        private val width = treeHeights[0].size
        private val height = treeHeights.size

        fun clownsToTheLeftOf(tree: Vector2d) = (tree.x - 1 downTo  0).map { Vector2d(it, tree.y) }
        fun jokersToTheRight(tree: Vector2d) = (tree.x + 1 until width).map { Vector2d(it, tree.y) }
        fun treesAbove(tree: Vector2d) = (tree.y - 1 downTo 0).map { Vector2d(tree.x, it) }
        fun treesBelow(tree: Vector2d) = (tree.y + 1 until height).map { Vector2d(tree.x, it) }

        fun treeIsVisible(tree: Vector2d): Boolean {

            return isEdgeOfForest(tree) || canSeeWoodForTheTrees(tree)
        }

        fun viewingDistance(tree: Vector2d, otherTrees: List<Vector2d>): Int {
            if (otherTrees.isEmpty()) return 0
            val treeHeight = getHeight(tree)
            val otherTreeCoordsHeights = otherTrees.zip(otherTrees.map { getHeight(it) })
            return tree.abs(otherTreeCoordsHeights.firstOrNull { it.second >= treeHeight }?.first ?: otherTrees.last())
        }

        fun scenicScore(tree: Vector2d): Int {
            val distanceAbove = viewingDistance(tree, treesAbove(tree))
            val distanceLeft = viewingDistance(tree, clownsToTheLeftOf(tree))
            val distanceRight = viewingDistance(tree, jokersToTheRight(tree))
            val distanceBelow = viewingDistance(tree, treesBelow(tree))
            return distanceAbove * distanceLeft * distanceRight * distanceBelow
        }

        private fun isEdgeOfForest(tree: Vector2d): Boolean {
            return tree.x == 0 || tree.x == width - 1 || tree.y == 0 || tree.y == height - 1
        }

        private fun canSeeWoodForTheTrees(tree: Vector2d): Boolean {
            val treeHeight = getHeight(tree)
            val heightsToTheLeft = getHeights(clownsToTheLeftOf(tree))
            val heightsToTheRight = getHeights(jokersToTheRight(tree))
            val heightsAbove = getHeights(treesAbove(tree))
            val heightsBelow = getHeights(treesBelow(tree))
            return heightsToTheLeft.all { it < treeHeight } || heightsToTheRight.all { it < treeHeight } || heightsAbove.all { it < treeHeight } || heightsBelow.all { it < treeHeight }
        }

        private fun allTrees(): List<Vector2d> {
            val coords = emptyList<Vector2d>().toMutableList()
            for (x in 0 until width) {
                for (y in 0 until height) {
                    coords.add(Vector2d(x, y))
                }
            }
            return coords
        }

        private fun getHeight(tree: Vector2d) = treeHeights[tree.y][tree.x]
        private fun getHeights(trees: List<Vector2d>) = trees.map { getHeight(it) }
        fun numVisibleTrees(): Int {
            return allTrees().map { treeIsVisible(it) }.count { it }
        }

        fun bestScenicScore(): Int {
            return allTrees().map { scenicScore(it) }.maxOf { it }
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