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

    fun getPair(): Bigram {
        val outBigram : Bigram
        when {
            first.posX == second.posX -> {
                val outDot1 = Dot(first.posX, (first.posY+1).rem(Params.COLUMNS))
                val outDot2 = Dot(second.posX, (second.posY+1).rem(Params.COLUMNS))
                outBigram = Bigram(outDot1, outDot2)
            }
            first.posY == second.posY -> {
                val outDot1 = Dot((first.posX+1).rem(Params.ROWS), first.posY)
                val outDot2 = Dot((second.posX+1).rem(Params.ROWS), second.posY)
                outBigram = Bigram(outDot1, outDot2)
            }
            else -> {
                val outDot1 = Dot(first.posX, second.posY)
                val outDot2 = Dot(second.posX, first.posY)
                outBigram = Bigram(outDot1, outDot2)
            }
        }
        print(outBigram)
        return outBigram
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