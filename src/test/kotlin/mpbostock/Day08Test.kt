package mpbostock

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day08Test {
    val testInput = listOf(
        "30373",
        "25512",
        "65332",
        "33549",
        "35390",
    )

    @Test
    fun `gets trees to left`() {
        val forest = Day08.Forest.read(testInput)
        val treesToLeft = forest.clownsToTheLeftOf(Vector2d(1, 1))
        assertEquals(1, treesToLeft.size)
        assertTrue(treesToLeft.contains(Vector2d(0, 1)))
    }

    @Test
    fun `gets trees to right`() {
        val forest = Day08.Forest.read(testInput)
        val treesToRight = forest.jokersToTheRight(Vector2d(1, 1))
        assertEquals(3, treesToRight.size)
        assertTrue(treesToRight.contains(Vector2d(2, 1)))
        assertTrue(treesToRight.contains(Vector2d(3, 1)))
        assertTrue(treesToRight.contains(Vector2d(4, 1)))
    }

    @Test
    fun `gets trees above`() {
        val forest = Day08.Forest.read(testInput)
        val treesAbove = forest.treesAbove(Vector2d(1, 1))
        assertEquals(1, treesAbove.size)
        assertTrue(treesAbove.contains(Vector2d(1, 0)))
    }

    @Test
    fun `gets trees below`() {
        val forest = Day08.Forest.read(testInput)
        val treesAbove = forest.treesBelow(Vector2d(1, 1))
        assertEquals(3, treesAbove.size)
        assertTrue(treesAbove.contains(Vector2d(1, 2)))
        assertTrue(treesAbove.contains(Vector2d(1, 3)))
        assertTrue(treesAbove.contains(Vector2d(1, 4)))
    }

    @Test
    fun `all outer trees are visible`() {
        val forest = Day08.Forest.read(testInput)
        assertTrue(forest.treeIsVisible(Vector2d(0, 0)))
        assertTrue(forest.treeIsVisible(Vector2d(1, 0)))
        assertTrue(forest.treeIsVisible(Vector2d(2, 0)))
        assertTrue(forest.treeIsVisible(Vector2d(3, 0)))
        assertTrue(forest.treeIsVisible(Vector2d(4, 0)))
        assertTrue(forest.treeIsVisible(Vector2d(0, 1)))
        assertTrue(forest.treeIsVisible(Vector2d(0, 2)))
        assertTrue(forest.treeIsVisible(Vector2d(0, 3)))
        assertTrue(forest.treeIsVisible(Vector2d(0, 4)))
        assertTrue(forest.treeIsVisible(Vector2d(4, 1)))
        assertTrue(forest.treeIsVisible(Vector2d(4, 2)))
        assertTrue(forest.treeIsVisible(Vector2d(4, 3)))
        assertTrue(forest.treeIsVisible(Vector2d(4, 4)))
        assertTrue(forest.treeIsVisible(Vector2d(1, 4)))
        assertTrue(forest.treeIsVisible(Vector2d(2, 4)))
        assertTrue(forest.treeIsVisible(Vector2d(3, 4)))
    }

    @Test
    fun `inner trees are visible`() {
        val forest = Day08.Forest.read(testInput)
        assertTrue(forest.treeIsVisible(Vector2d(1, 1)))
        assertTrue(forest.treeIsVisible(Vector2d(2, 1)))
        assertTrue(forest.treeIsVisible(Vector2d(1, 2)))
        assertTrue(forest.treeIsVisible(Vector2d(3, 2)))
        assertTrue(forest.treeIsVisible(Vector2d(2, 3)))
    }

    @Test
    fun `inner trees are not visible`() {
        val forest = Day08.Forest.read(testInput)
        assertFalse(forest.treeIsVisible(Vector2d(1, 3)))
        assertFalse(forest.treeIsVisible(Vector2d(2, 2)))
        assertFalse(forest.treeIsVisible(Vector2d(3, 1)))
        assertFalse(forest.treeIsVisible(Vector2d(3, 3)))
    }

    @Test
    fun `furthest viewing distances up, left, down and right`() {
        val forest = Day08.Forest.read(testInput)
        val tree = Vector2d(2, 1)
        val distanceAbove = forest.viewingDistance(tree, forest.treesAbove(tree))
        val distanceLeft = forest.viewingDistance(tree, forest.clownsToTheLeftOf(tree))
        val distanceRight = forest.viewingDistance(tree, forest.jokersToTheRight(tree))
        val distanceBelow = forest.viewingDistance(tree, forest.treesBelow(tree))
        assertEquals(1, distanceAbove)
        assertEquals(1, distanceLeft)
        assertEquals(2, distanceRight)
        assertEquals(2, distanceBelow)
    }

    @Test
    fun `scenic score is 4`() {
        val forest = Day08.Forest.read(testInput)
        val tree = Vector2d(2, 1)
        val scenicScore = forest.scenicScore(tree)
        assertEquals(4, scenicScore)
    }

    @Test
    fun `scenic score is 8`() {
        val forest = Day08.Forest.read(testInput)
        val tree = Vector2d(2, 3)
        val scenicScore = forest.scenicScore(tree)
        assertEquals(8, scenicScore)
    }

    @Test
    fun `partOne for test data`() {
        assertEquals(21, Day08.partOne(testInput))
    }

    @Test
    fun `partTwo for test data`() {
        assertEquals(8, Day08.partTwo(testInput))
    }
}