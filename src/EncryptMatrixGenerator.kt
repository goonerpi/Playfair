import java.io.*

/*fun String.read() :String? {
    val reader = BufferedReader(FileReader(this))
    val keyword = reader.readLine().toString().toUpperCase()
    reader.close()
    println(keyword)
    return keyword
}*/

object Params{
    val ROWS = 6
    val COLUMNS = 5
    val alphabet = mutableListOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '.', ',', ' ', '-')
}

class EncryptMatrixGenerator {


    fun generate (dest: String) :  MutableList<MutableList<Char>> {
        val matrix: MutableList<MutableList<Char>> = mutableListOf(mutableListOf())
        val keyword = readFromFile(dest)
        val remain = Params.alphabet
        val result: MutableList<Char> = mutableListOf()
        for (c in keyword) {
            if (remain.contains(c)) {
                result += c
                remain.remove(c)
            }
        }
        result += remain

        for (i in 0 until 5*Params.ROWS step 5){
            val line : MutableList<Char> = mutableListOf()
            (0 until Params.COLUMNS).mapTo(line) { result[i+ it] }
            matrix.add(line)
        }

        matrix.removeAt(0)
        println("    Matrix:")
        for (i in 0 until Params.ROWS) println(matrix[i])
        return matrix

    }
}