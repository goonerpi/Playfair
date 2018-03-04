import java.io.*

fun String.read() :String? {
    val reader = BufferedReader(FileReader(this))
    val keyword = reader.readLine().toString().toUpperCase()
    reader.close()
    println(keyword)
    return keyword
}

class EncryptMatrixGenerator {

    private val ROWS = 6
    private val COLUMNS = 5
    var matrix: MutableList<MutableList<Char>> = mutableListOf(mutableListOf())
    private val alphabet = mutableListOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '.', ',', ' ', '-')



    fun generate(dest: String) {
        val keyword = readFromFile(dest)
        val remain = alphabet
        val result: MutableList<Char> = mutableListOf()
        for (c in keyword) {
            if (remain.contains(c)) {
                result.add(c)
                remain.remove(c)
            }
        }
        result += remain

        for (i in 0 until 5*ROWS step 5){
            val line : MutableList<Char> = mutableListOf()
            (0 until COLUMNS).mapTo(line) { result[i+ it] }
            matrix.add(line)
        }

        matrix.removeAt(0)
        println("    Matrix:")
        for (i in 0 until ROWS) println(matrix[i])

    }
}