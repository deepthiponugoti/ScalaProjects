import org.junit.Assert._
import org.junit.Test

class RodCutterTest{

  val prices = Map(1 -> 1, 2 -> 2, 3 -> 3, 4 -> 4)

	@Test
	def testCanary = {
		assertTrue(true)
	}

	@Test
	def testForGetOptimalCutsForZeroLength= {
    val expected = (0, List(List()))
		assertEquals(expected, RodCutter.getOptimalCuts(0, prices))
	}
	
	@Test
	def testForGetOptimalCutsForOneLength= {
    val expected = (1, List(List(1)))
		assertEquals(expected, RodCutter.getOptimalCuts(1, prices))
	}

  @Test
  def testForGetOptimalCutsForLengthTwo = {
    val expected = (2, List(List(2), List(1, 1)))
    assertEquals(expected, RodCutter.getOptimalCuts(2, prices))
  }

  @Test
  def testForGetOptimalCutsForLengthThree = {
    val expected = (3, List(List(3), List(1, 2), List(1, 1, 1)))
    assertEquals(expected, RodCutter.getOptimalCuts(3, prices))
  }

  @Test
  def testForGetOptimalCutsForLengthFour = {
    val expected = (4, List(List(4), List(1, 3), List(1, 1, 2), List(1, 1, 1, 1), List(2, 2)))
    assertEquals(expected, RodCutter.getOptimalCuts(4, prices))
  }

  @Test
  def testForGetOptimalCutsForPriceListWhereSolutionDoesNotIncludeLengthItself = {
    val expected = (8, List(List(2, 2)))
    assertEquals(expected, RodCutter.getOptimalCuts(4, Map(1 -> 1, 2 -> 4, 3 -> 4, 4 -> 5)))
  }

  @Test
  def testForGetOptimalCutsForPriceListWhereSomePricesAreMissing = {
    val expected = (10,List(List(1, 1, 3, 3)))
    assertEquals(expected, RodCutter.getOptimalCuts(8, Map(1 -> 1, 3 -> 4, 5 -> 5, 7 -> 7)))
  }
}