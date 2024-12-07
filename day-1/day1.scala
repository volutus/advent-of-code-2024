import scala.collection.*

val Delimiter = "   "

object day1 
{
    def main(args: Array[String]) = 
    {   
        val source = scala.io.Source.fromFile("day-1/input.txt")
        try 
        {
            // Part 1: Difference score
            var left = new mutable.ListBuffer[Int]
            var right = new mutable.ListBuffer[Int]
            val lineIterator = source.getLines()
            for (line <- lineIterator)
            {
                val parts = line.split(Delimiter)
                
                val leftValue = parts(0)
                left.addOne(leftValue.toInt)
                
                val rightValue = parts(1)
                right.addOne(rightValue.toInt)
            }
            left = left.sorted
            right = right.sorted

            var i = 0
            var sum = 0
            val length = Math.min(left.length, right.length)
            while (i < length)
            {
                val leftValue = left(i)
                val rightValue = right(i)

                val diff = Math.abs(leftValue - rightValue)
                sum += diff
                i += 1
            }

            println("Part 1: " + sum)

            // Part 2: Similarity score
            i = 0 
            var similarity = 0
            while (i < length)
            {
                val value = left(i)
                val freq = right.count(_ == value)
                similarity += (freq * value)
                i += 1
            }

            println("Part 2: " + similarity)
        }
        finally source.close()
    }
}