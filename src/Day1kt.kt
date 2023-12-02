fun main() {
    val lines = arrayOf("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet")
    print("The sun is ${getCalibrationValue(lines)}")
}

private fun getCalibrationValue(lines: Array<String>): Int {
    var sum = 0;

    // find the sum of each line
    for (line in lines) {
        var metFirst = false
        var firstNumber = 0
        var curNumber = 0

        // find the first and last two letters
        for (letter in line) {
            if (letter in '0'..'9') {
                curNumber = letter - '0'

                if (!metFirst) {
                    firstNumber = curNumber
                    metFirst = true
                }
            }
        }

        sum += firstNumber * 10 + curNumber
    }

    return sum
}