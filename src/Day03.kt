import kotlin.experimental.inv
import kotlin.math.pow

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

    val input = readInput("Day03")

    val bitLength = input.first()

    // part 1
    var gammaRate = 0
    var epsilonRate = 0
    var powerConsumption = 0
    var countLines = 0
    var arrayInt = IntArray(bitLength.length)

    for (binary in input) {
        var counter = bitLength.length-1
        countLines++
        for (int in binary) {
            if (int == '1') {
                arrayInt[counter] = arrayInt[counter] + 1
            } else {
                arrayInt[counter] = arrayInt[counter] - 1
            }
            counter--
        }
    }

    for (index in 0..arrayInt.size-1) {
        if (arrayInt[index] > 0) {
            gammaRate += 2.toDouble().pow(index).toInt()
        } else {
            epsilonRate += 2.toDouble().pow(index).toInt()
        }
    }

    println(arrayInt.contentToString())
    powerConsumption = gammaRate * epsilonRate
    println("Powerconsumption of submarine: $powerConsumption")

    // part 2
    val oxygenGenerator = searchBinaryMostCommon(0, input)
    val carbonDiOxidScrubber = searchBinaryLeastCommon(0, input)

    println("Oxygen generator value : $oxygenGenerator")
    println("C02 scrubber value : $carbonDiOxidScrubber")
    val lifeSupportRating = binaryStringToInt(carbonDiOxidScrubber) * binaryStringToInt(oxygenGenerator)
    println("Lifesupport ratring: $lifeSupportRating")

}

fun searchBinaryMostCommon(index: Int, toBeSortedArray: List<String>): String {
    val newSortList =  mutableListOf<String>()
    var varIndex = index
    var mostCommon = 0
    var sizeOfBinary = toBeSortedArray[0].length

    for (sortBinary in toBeSortedArray) {
        // list of numbers
        if (sortBinary[varIndex] == '1') {
            mostCommon++
        } else {
            mostCommon--
        }
    }
    for (sortBinary in toBeSortedArray) {
        if (mostCommon >= 0 && sortBinary[varIndex] == '1') {
            newSortList.add(sortBinary)
        }
        if (mostCommon < 0 && sortBinary[varIndex] == '0') {
            newSortList.add(sortBinary)
        }
    }
    varIndex++
    /*
    println("${index+1}. cyle")
    if (mostCommon >= 0) {
        println("Mostcommon is: 1")
    } else {
        println("Mostcommon is: 0")
    }
    for (elem in newSortList) {
        println (elem)
    }
    */
    return if (newSortList.size == 1 || varIndex == sizeOfBinary) {
        newSortList[0]
    } else {
        searchBinaryMostCommon(varIndex, newSortList)
    }
}

fun searchBinaryLeastCommon(index: Int, toBeSortedArray: List<String>): String {
    val newSortList =  mutableListOf<String>()
    var varIndex = index
    var leastCommon = 0
    var sizeOfBinary = toBeSortedArray[0].length

    for (sortBinary in toBeSortedArray) {
        // list of numbers
        if (sortBinary[varIndex] == '1') {
            leastCommon--
        } else {
            leastCommon++
        }
    }
    for (sortBinary in toBeSortedArray) {
        if (leastCommon > 0 && sortBinary[varIndex] == '1') {
            newSortList.add(sortBinary)
        }
        if (leastCommon <= 0 && sortBinary[varIndex] == '0') {
            newSortList.add(sortBinary)
        }
    }
    varIndex++
    /*
    println("${index+1}. cyle")
    if (leastCommon > 0) {
        println("Leastcommon is: 1")
    } else {
        println("Leastcommon is: 0")
    }
    for (elem in newSortList) {
        println (elem)
    }
    */
    return if (varIndex == sizeOfBinary || newSortList.size == 1) {
        newSortList[0]
    } else {
        searchBinaryLeastCommon(varIndex, newSortList)
    }
}

fun binaryStringToInt(convertable: String) : Int {
    var power = convertable.length-1
    var result = 0
    for (elem in convertable) {
        if (elem == '1') {
            result += 2.toDouble().pow(power).toInt()
        }
        power--
    }
    return result
}

