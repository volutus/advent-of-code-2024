import scala.collection.*
import scala.collection.mutable.ListBuffer

object day2 
{
    def main(args: Array[String]) = 
    {
        var safeCount = 0   
        val allReports = readFile()
        for (report <- allReports)
        {
            var increment = if (reportIsSafe(report)) 1 else 0
            if (increment == 0)
            {
                val closeEnough = checkDampenedReports(report)
                increment = if (closeEnough) 1 else 0
            }
            safeCount += increment
        }
        print("Safety score: " + safeCount)

    }

    def readFile() : List[List[Int]] = 
    {
        val reportsBuffer = new ListBuffer[List[Int]]
        val source = scala.io.Source.fromFile("day-2/input.txt")
        try 
        {
            val lineIterator = source.getLines()
            for (line <- lineIterator)
            {   
                val numbers = new ListBuffer[Int]
                val parts = line.split(" ")
                for (rawNumber <- parts)
                {
                    numbers.addOne(rawNumber.toInt)
                }
                reportsBuffer.addOne(numbers.toList)
            }
        }
        finally source.close()
        return reportsBuffer.toList
    }

    def reportIsSafe(report : List[Int]) : Boolean = 
    {
        val ascending = report.sorted
        val descending = report.sortBy(_.toInt)(Ordering[Int].reverse)
        if (report != ascending && report != descending)
        {
            return false;
        }

        var i = 1
        while (i < report.length)
        {
            val prev = report(i-1)
            val curr = report(i)
            val diff = Math.abs(prev - curr)
            if (diff <= 0 || diff > 3)
            {
                return false;
            }

            i += 1
        }

        return true;
    }

    def checkDampenedReports(report : List[Int]) : Boolean = 
    {
        var i = 0
        while (i < report.length)
        {
            val alternateReport = report.patch(i, Nil, 1)
            if (reportIsSafe(alternateReport))
            {
                return true;
            }
            i += 1
        }
        return false;
    }
}