fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 1)

    val input = readInput("Day02")

    var basicCommand: List<String>
    var horizontalValue = 0
    var depthValue = 0
    var aim = 0
    var productPosition = 0

    for (command in input) {
        basicCommand = command.split(" ", ignoreCase = false, limit = 2)
        when (basicCommand[0]) {
            "forward" -> {
                horizontalValue += basicCommand[1].toInt()
                depthValue += aim*basicCommand[1].toInt()
            }
            "down" -> aim += basicCommand[1].toInt()
            "up" -> aim -= basicCommand[1].toInt()
            else -> println("No valid command!")
        }
    }

    productPosition = horizontalValue * depthValue
    println ("Horizontal position: $horizontalValue")
    println ("Depth position: $depthValue")
    println ("Product position: $productPosition")
    //println(part1(input))
    //println(part2(input))
}
