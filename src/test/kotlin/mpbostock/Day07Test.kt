package mpbostock

import mpbostock.Day07.Directory
import mpbostock.Day07.File
import mpbostock.Day07.cd
import mpbostock.Day07.isCommand
import mpbostock.Day07.ls
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day07Test {
    val testInput = listOf(
    "$ cd /",
    "$ ls",
    "dir a",
    "14848514 b.txt",
    "8504156 c.dat",
    "dir d",
    "$ cd a",
    "$ ls",
    "dir e",
    "29116 f",
    "2557 g",
    "62596 h.lst",
    "$ cd e",
    "$ ls",
    "584 i",
    "$ cd ..",
    "$ cd ..",
    "$ cd d",
    "$ ls",
    "4060174 j",
    "8033020 d.log",
    "5626152 d.ext",
    "7214296 k",
    )

    @Test
    fun `directory with single file has size of file`() {
        val dirE = Directory("e").addChild(File("i", 584))
        assertEquals(584, dirE.size)
    }

    @Test
    fun `directory with files and sub directory with files has total size of all files`() {
        val fileBTxt = File("b.txt", 14848514)
        val fileCDat = File("c.dat", 8504156)
        val fileF = File("f", 29116)
        val fileG = File("g", 2557)
        val fileHLst = File("h.lst", 62596)
        val fileI = File("i", 584)
        val fileJ = File("j", 4060174)
        val fileDLog = File("d.log", 8033020)
        val fileDExt = File("d.ext", 5626152)
        val fileK = File("k", 7214296)
        val dirE = Directory("e").addChild(fileI)
        val dirA = Directory("a").addChildren(dirE, fileF, fileG, fileHLst)
        val dirD = Directory("d").addChildren(fileJ, fileDLog, fileDExt, fileK)
        val root = Directory.createRoot().addChildren(dirA, fileBTxt, fileCDat, dirD)
        assertEquals(94853, dirA.size)
        assertEquals(24933642, dirD.size)
        assertEquals(48381165, root.size)
    }

    @Test
    fun `directories less than 100000 are a and e`() {
        val fileBTxt = File("b.txt", 14848514)
        val fileCDat = File("c.dat", 8504156)
        val fileF = File("f", 29116)
        val fileG = File("g", 2557)
        val fileHLst = File("h.lst", 62596)
        val fileI = File("i", 584)
        val fileJ = File("j", 4060174)
        val fileDLog = File("d.log", 8033020)
        val fileDExt = File("d.ext", 5626152)
        val fileK = File("k", 7214296)
        val dirE = Directory("e").addChild(fileI)
        val dirA = Directory("a").addChildren(dirE, fileF, fileG, fileHLst)
        val dirD = Directory("d").addChildren(fileJ, fileDLog, fileDExt, fileK)
        val root = Directory.createRoot().addChildren(dirA, fileBTxt, fileCDat, dirD)
        val lowerThan100000 = root.flattenedChildren.filter { it is Directory && it.size <= 100000 }
        assertEquals(2, lowerThan100000.size)
        assertEquals(dirA, lowerThan100000[0])
        assertEquals(dirE, lowerThan100000[1])
    }

    @Test
    fun `list command adds contents to current directory`() {
        val currentDirectory = Directory.createRoot()
        ls(testInput.drop(2).takeWhile { !it.isCommand() }, currentDirectory)
        assertEquals(4, currentDirectory.children.size)
        assertEquals(Directory("a"), currentDirectory.children[0])
        assertEquals(File("b.txt", 14848514), currentDirectory.children[1])
        assertEquals(File("c.dat", 8504156), currentDirectory.children[2])
        assertEquals(Directory("d"), currentDirectory.children[3])
    }

    @Test
    fun `cd up command changes current directory to parent`() {
        val childDir = Directory("child")
        val parentDir = Directory("parent").addChild(childDir)
        var currentDir = childDir
        currentDir = cd(currentDir, "$ cd ..")
        assertEquals(parentDir, currentDir)
    }

    @Test
    fun `cd command changes current directory to child`() {
        val childDir = Directory("child")
        val parentDir = Directory("parent")
        parentDir.addChild(childDir)
        var currentDir = parentDir
        currentDir = cd(currentDir, "$ cd child")
        assertEquals(childDir, currentDir)
    }

    @Test
    fun `part one for test input`() {
        assertEquals(95437, Day07.partOne(testInput))
    }

    @Test
    fun `part two for test input`() {
        assertEquals(24933642, Day07.partTwo(testInput))
    }
}