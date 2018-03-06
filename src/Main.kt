const val INPUT_KEYWORD = "C:\\Users\\pavel\\IdeaProjects\\Playfair\\src\\keyword.txt"
const val INPUT_TEXT = "C:\\Users\\pavel\\IdeaProjects\\Playfair\\src\\input.txt"
const val OUTPUT_TEXT_ENCODED = "C:\\Users\\pavel\\IdeaProjects\\Playfair\\encoded.txt"
const val OUTPUT_TEXT_DECODED = "C:\\Users\\pavel\\IdeaProjects\\Playfair\\decoded.txt"




    fun main(args : Array<String>){

        val playfair = Playfair()
        playfair.encode()
        playfair.decode()
        print("Ez")
    }
