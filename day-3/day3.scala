import scala.collection.*
import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex

object day3
{
    val MemoryRegex: Regex = "mul\\(\\d{1,},\\d{1,}\\)".r
    def main(args: Array[String]) = 
    {
        var sum = 0
        val originalMemory = readFile()
        val memory = removeDisabledInstructions(originalMemory)
        val matches = MemoryRegex.findAllMatchIn(memory)
        for (m <- matches)
        {
            val str = m.toString()
            val start = str.indexOf("(")
            val middle = str.indexOf(",")
            val end = str.indexOf(")")

            val left = str.substring(start + 1, middle).toInt
            val right = str.substring(middle + 1, end).toInt
            sum += left * right
        }
        println("Solution: " + sum)
    }

    def readFile() : String = 
    {
        val source = scala.io.Source.fromFile("day-3/input.txt")
        val lines = try source.mkString finally source.close()
        return lines
    }

    def removeDisabledInstructions(memory: String) : String = 
    {
        val words = List("don't()", "do()")
        val sb = new StringBuilder()
        var write = true
        var capture = new StringBuilder()
        for (c <- memory)
        {
            capture.append(c)

            var continueCapture = false; 
            var fullMatch = false;
            for (word <- words)
            {
                if (word.startsWith(capture.toString()))
                {
                    continueCapture = true;
                }
            }
            if (!continueCapture)
            {
                capture = new StringBuilder()
            }
            else
            {
                if (capture.toString().equals("don't()"))
                {
                    write = false;
                    capture = new StringBuilder()
                }
                else if (capture.toString().equals("do()"))
                {
                    write = true;
                    capture = new StringBuilder()
                }
            }

            if (write)
            {
                sb.append(c);
            }
        }
        return sb.toString()
    }
}