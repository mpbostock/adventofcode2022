package mpbostock

object Day07 {
    fun String.isCommand(): Boolean = this.startsWith('$')
    fun String.isLs(): Boolean = this == "$ ls"
    fun String.isCd(): Boolean = this.startsWith("$ cd")

    interface TreeNode {
        val size: Int
        val name: String
        var parent: TreeNode?
        val children: ArrayList<TreeNode>
        val flattenedChildren: List<TreeNode>
            get() = children + children.flatMap { it.flattenedChildren }

        fun addChild(child: TreeNode): TreeNode {
            child.parent = this
            children.add(child)
            return this
        }

        fun addChildren(vararg children: TreeNode): TreeNode {
            children.forEach { addChild(it) }
            return this
        }

        fun findByName(name: String): TreeNode = children.single { it.name == name }
    }

    abstract class BaseTreeNode(
        override val name: String, override var parent: TreeNode? = null,
        override val children: ArrayList<TreeNode> = arrayListOf()
    ) : TreeNode

    data class Directory(override val name: String) : BaseTreeNode(name) {
        override val size: Int
            get() = children.sumOf { it.size }

        companion object {
            fun createRoot() = Directory("/")
            fun fromConsole(line: String): Directory {
                val split = line.split(' ')
                return Directory(split[1])
            }
        }
    }

    data class File(override val name: String, override val size: Int) : BaseTreeNode(name) {
        companion object {
            fun fromConsole(line: String): File {
                val split = line.split(' ')
                return File(split[1], split[0].toInt())
            }
        }
    }

    fun ls(lines: List<String>, currentDirectory: Directory) {
        lines.forEach {
            when {
                it.startsWith("dir") -> currentDirectory.addChild(Directory.fromConsole(it))
                else -> currentDirectory.addChild(File.fromConsole(it))
            }
        }
    }

    fun cd(currentDirectory: Directory, line: String) =
        (currentDirectory.parent.takeIf { line == "$ cd .." } ?: currentDirectory.findByName(line.split(' ')[2])) as Directory

    fun parseConsoleOutput(input: List<String>): Directory {
        val rootDir = Directory.createRoot()
        var currentDirectory = rootDir
        tailrec fun parseLine(lines: List<String>) {
            if (lines.isEmpty()) return
            val line = lines.take(1).first()
            var dropNumLines = 1
            when {
                line.isLs() -> {
                    val listLines = lines.drop(1).takeWhile { !it.isCommand() }
                    ls(listLines, currentDirectory)
                    dropNumLines += listLines.size
                }
                line.isCd() -> {
                    currentDirectory = cd(currentDirectory, line)
                }
            }
            parseLine(lines.drop(dropNumLines))
        }
        parseLine(input.drop(1))
        return rootDir
    }

    fun partOne(input: List<String>): Int {
        val rootDir = parseConsoleOutput(input)
        return rootDir.flattenedChildren.filter { it is Directory && it.size <= 100000 }.sumOf { it.size }
    }

    fun partTwo(input: List<String>): Int {
        val rootDir = parseConsoleOutput(input)
        val unusedSpace = 70_000_000 - rootDir.size
        return rootDir.flattenedChildren.filter { it is Directory && it.size + unusedSpace > 30_000_000 }.minOf { it.size }
    }

    private val input = FileIO.readInput("day07input.txt") { s -> s }

    @JvmStatic
    fun main(args: Array<String>) {
        val partOneSolution = partOne(input)
        println(partOneSolution)

        val partTwoSolution = partTwo(input)
        println(partTwoSolution)
    }
}