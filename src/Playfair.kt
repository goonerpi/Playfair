import java.io.*

val matrix = EncryptMatrixGenerator().generate(INPUT_KEYWORD)

fun readFromFile(readFrom : String) : String{
    val br = BufferedReader(FileReader(readFrom))
    br.use {
        val sb = StringBuilder()
        var line = it.readLine()

        while (line != null){
            sb.append(line)
            sb.append("=")
            line = it.readLine()

        }
        sb.deleteCharAt(sb.length-1)
        val everything = sb.toString().replace(",", "").toUpperCase()
        println(everything)
        return everything
    }
}

data class Dot(val posX : Int, val posY :Int){

    val char : Char
        get() = matrix[posX][posY]
}

class Bigram(val first : Dot, val second : Dot){

    fun encodeBigram(): Bigram {
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
        return outBigram
    }

    fun decodeBigram(): Bigram {
        val outBigram : Bigram
        when {
            first.posX == second.posX -> {
                val outDot1 = Dot(first.posX, (first.posY + (Params.COLUMNS - 1)).rem(Params.COLUMNS))
                val outDot2 = Dot(second.posX, (second.posY + (Params.COLUMNS - 1)).rem(Params.COLUMNS))
                outBigram = Bigram(outDot1, outDot2)
            }
            first.posY == second.posY -> {
                val outDot1 = Dot((first.posX + (Params.ROWS - 1)).rem(Params.ROWS), first.posY)
                val outDot2 = Dot((second.posX + (Params.ROWS - 1)).rem(Params.ROWS), second.posY)
                outBigram = Bigram(outDot1, outDot2)
            }
            else -> {
                val outDot1 = Dot(first.posX, second.posY)
                val outDot2 = Dot(second.posX, first.posY)
                outBigram = Bigram(outDot1, outDot2)
            }
        }
        return outBigram
    }

    fun convertToText() : String = StringBuilder().append(first.char).append(second.char).toString()
}

class Playfair{

    var text : String = readFromFile(INPUT_TEXT)
    //val bigramSet = mutableListOf<Bigram>()

    fun performText(){
        var isCheckedOdd = false
        var isCheckedRep = false

        fun checkForOdd() {
            isCheckedOdd = true
            if (text.length % 2 != 0) {
                text += '-'
                isCheckedOdd = false
            }
        }

        fun checkForRepetitive(){
            isCheckedRep = true
            for (i in 0 until text.length-1 step 2){
                if (text[i] == text[i+1]) {
                    text = StringBuffer(text).insert(i+1,'-').toString()
                    isCheckedRep = false
                }
            }
        }

        do {
            checkForRepetitive()
            checkForOdd()
        }
        while (!isCheckedOdd || !isCheckedRep)

        println("New text : $text")
    }

    fun findPosByChar(c : Char) : MutableList<Int>{
        var kek = mutableListOf<Int>()
        for (i in 0 until Params.ROWS)
            for (j in 0 until Params.COLUMNS)
                if (c == matrix[i][j]) kek = mutableListOf(i,j)
        return kek
    }

    /*fun createBigramSet(){
        //val bigramSet = mutableListOf<Bigram>()
        for (c in 0 until text.length-1 step 2) {
            val pos1 = findPosByChar(text[c])
            val pos2 = findPosByChar(text[c + 1])
            val dot1 = Dot(pos1[0], pos1[1])
            val dot2 = Dot(pos2[0], pos2[1])
            val bigram = Bigram(dot1, dot2)
            bigramSet.add(bigram)
        }
    }*/ //in case of need

    fun encode(){
        performText()
        var outTextEncoded = ""
        for (c in 0 until text.length-1 step 2) {
            val pos1 = findPosByChar(text[c])
            val pos2 = findPosByChar(text[c + 1])
            val dot1 = Dot(pos1[0], pos1[1])
            val dot2 = Dot(pos2[0], pos2[1])
            val bigram = Bigram(dot1, dot2)
            outTextEncoded += bigram.encodeBigram().convertToText()
        }
        println("\nEncoded : $outTextEncoded")
        File(OUTPUT_TEXT_ENCODED).bufferedWriter().use { out -> out.write(outTextEncoded) }

    }

    fun decode(){
        val outTextEncoded = readFromFile(OUTPUT_TEXT_ENCODED)
        var outTextDecoded = ""
        for (c in 0 until outTextEncoded.length-1 step 2) {
            val pos1 = findPosByChar(outTextEncoded[c])
            val pos2 = findPosByChar(outTextEncoded[c + 1])
            val dot1 = Dot(pos1[0], pos1[1])
            val dot2 = Dot(pos2[0], pos2[1])
            val bigram = Bigram(dot1, dot2)
            outTextDecoded += bigram.decodeBigram().convertToText().replace('=', '\n').replace("-", "").toLowerCase()
        }

        println("\nDecoded : $outTextDecoded")
        File(OUTPUT_TEXT_DECODED).bufferedWriter().use { out -> out.write(outTextDecoded) }
    }



}