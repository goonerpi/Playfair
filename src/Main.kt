import java.io.*

const val INPUT_KEYWORD = "C:\\Users\\pavel\\IdeaProjects\\Playfair\\src\\keyword.txt"
const val INPUT_TEXT = "C:\\Users\\pavel\\IdeaProjects\\Playfair\\src\\input.txt"
const val OUTPUT_TEXT = "C:\\Users\\pavel\\IdeaProjects\\Playfair\\src\\output.txt"



    fun main(args : Array<String>){

        val playfair = Playfair()
        playfair.performText()
        playfair.encode()

    }
