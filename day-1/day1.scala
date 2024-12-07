import scala.collection.*

val ThreeSpacesDelimiter = "   "

object day1 
{
    def main(args: Array[String]) = 
    {   
        val (leftList, rightList) = readFile()
        var sum = 0
        var similarity = 0
        for ( (left, right) <- (leftList zip rightList))
        {
            val diff = Math.abs(left - right)
            sum += diff
            
            val freq = rightList.count(_ == left)
            similarity += (freq * left)
        }
        println("Part 1: " + sum)
        println("Part 2: " + similarity)
    }

    def readFile() : (List[Int], List[Int]) = 
    {
        val source = scala.io.Source.fromFile("day-1/input.txt")
        val leftBuffer = new mutable.ListBuffer[Int]
        val rightBuffer = new mutable.ListBuffer[Int]
        try 
        {
            val lineIterator = source.getLines()
            for (line <- lineIterator)
            {
                val parts = line.split(ThreeSpacesDelimiter)
                
                val leftValue = parts(0)
                leftBuffer.addOne(leftValue.toInt)
                
                val rightValue = parts(1)
                rightBuffer.addOne(rightValue.toInt)
            }
        }
        finally source.close()

        val left = leftBuffer.sorted.toList
        val right = rightBuffer.sorted.toList
        return (left, right)
    }
}