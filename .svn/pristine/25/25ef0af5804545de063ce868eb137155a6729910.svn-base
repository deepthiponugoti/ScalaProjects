import scala.annotation.tailrec

class FibonacciSeries {

  def fibonacciRecursion(position: Int): List[Int] = position match {
    case 0 => List(1)
    case 1 => List(1, 1)
    case _ if position < 0 => List()
    case _ => {
      val listAtPositionMinusOne = fibonacciRecursion(position - 1)
      listAtPositionMinusOne :+ listAtPositionMinusOne.takeRight(2).sum
    }
  }

  @tailrec
  final def fibonacciTailRecursion(position: Int, listOfNumbers: List[Int] = List(1)): List[Int] = position match {
    case 0 => listOfNumbers
    case _ => fibonacciTailRecursion(position - 1, listOfNumbers :+ listOfNumbers.takeRight(2).sum)
  }
}