package funsets

/**
 * Currying function
 *
 */

object functions3 {

  // Shorter form
  def sum(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sum(f)(a + 1, b)     //> sum: (f: Int => Int)(a: Int, b: Int)Int

  sum(x => x)(1, 3)                               //> res0: Int = 6

  //Or other form
  def sum1(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      if (a > b) 0 else f(a) + sumF(a + 1, b)
    sumF
  }                                               //> sum1: (f: Int => Int)(Int, Int) => Int

  def sumInts = sum1(x => x)                      //> sumInts: => (Int, Int) => Int
  sumInts(1, 3)                                   //> res1: Int = 6
}