object RodCutter {

  def getOptimalCutsForSplit(split: Int, length: Int, prices: Map[Int, Int]): (Int, List[List[Int]])= {
    val resultForFirstSplit = getOptimalCuts(split, prices)
    val resultForSecondSplit = getOptimalCuts(length - split, prices)
    val revenue = resultForFirstSplit._1 + resultForSecondSplit._1
    val permutationOfBothCuts =
      for (cutFromTheFirstSplit <- resultForFirstSplit._2; cutFromTheSecondSplit <- resultForSecondSplit._2) yield
      (cutFromTheFirstSplit ::: cutFromTheSecondSplit).sorted

    (revenue, permutationOfBothCuts)
  }

  def maxOfCuts(firstCut: (Int, List[List[Int]]), secondCut: (Int, List[List[Int]])): (Int, List[List[Int]]) = {
    firstCut._1 match {
      case _ if firstCut._1 > secondCut._1 => firstCut
      case _ if firstCut._1 == secondCut._1 => (firstCut._1, firstCut._2 ::: secondCut._2)
      case _ => secondCut
    }
  }

  def getOptimalCuts(length: Int, prices: Map[Int, Int]) : (Int, List[List[Int]]) = {
    length match {
      case _ if length <= 0 => (0, List(List()))
      case _ =>
	      val profitAndCutOfGivenLength = (prices.getOrElse(length, 0), List(List(length)))
        val combinedListWithMaxRevenue = Stream.range(1, length).foldLeft(profitAndCutOfGivenLength) {
                  (maxOfLastTwoCuts, split) => maxOfCuts(maxOfLastTwoCuts, getOptimalCutsForSplit(split, length, prices))
        }

        (combinedListWithMaxRevenue._1, combinedListWithMaxRevenue._2.distinct)
    }
  }
}