import java.io.*

val matrix = EncryptMatrixGenerator().generate(INPUT_KEYWORD)

fun readFromFile(readFrom : String) : String{
    val br = BufferedReader(FileReader(readFrom))
    br.use {
        val sb = StringBuilder()
        var line = it.readLine()

        while (line != null){
            sb.append(line)
            sb.append(" ")
            line = it.readLine()

        }
        sb.deleteCharAt(sb.length-1)
        val everything = sb.toString().toUpperCase()
        println(everything)
        return everything
    }
}

data class Dot(val posX : Int, val posY :Int){
    val char : Char
        get() = matrix[posX][posY]

}

class Bigram(val first : Dot, val second : Dot){

    fun getPair(input : Bigram){
        if (first.posX == second.posX){
            val out1 = Dot()
        }
    }
}

class Playfair{

    var text : String = readFromFile(INPUT_TEXT)

    fun performText(){
        var isCheckedOdd = false
        var isCheckedRep = false

        fun checkForOdd() {
            isCheckedOdd = true
            if (text.length % 2 != 0) {
                text += 'X'
                isCheckedOdd = false
            }
        }

        fun checkForRepetitive(){
            isCheckedRep = true
            for (i in 0 until text.length-1 step 2){
                if (text[i] == text[i+1]) {
                    text = StringBuffer(text).insert(i+1,'X').toString()
                    isCheckedRep = false
                }
            }
        }

        do {
            checkForRepetitive()
            checkForOdd()
        }
        while (!isCheckedOdd || !isCheckedRep)

        print("New text : $text")
    }



}