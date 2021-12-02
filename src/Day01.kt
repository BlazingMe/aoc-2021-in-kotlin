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

    val input = readInput("Day01")
    var numberOfIncs = 0
    var numberOfSumIncs = 0
    var lastNumber = ""
    var window = arrayOf(0,0,0,0)
    var count = 0
    var sum = 0
    var lastSum = 0

    for (number in input) {
        if ((!lastNumber.equals("")) && (lastNumber.toInt() <= number.toInt())) {
            numberOfIncs++
        }
        count++
        for (i in 0..3) {
            if (((count - i) % 4) >= 1) {
                window[i] = window[i] + number.toInt()
                if (((count - i) % 4) == 3) {
                    sum = window[i]
                    window[i] = 0
                }
                if ((sum > lastSum) && (lastSum != 0)) {
                    numberOfSumIncs++
                }
                lastSum = sum
            }
        }
        lastNumber = number
    }
    println(numberOfIncs)
    println(numberOfSumIncs)
    //println(part1(input))
    //println(part2(input))
}
