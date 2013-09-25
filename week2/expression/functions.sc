package funsets

/**
 * Simple Recursive Functions for some mathmatical expression
 *
 */
object functions {

  def sumInts(a: Int, b: Int): Int = if (a > b) 0 else a + sumInts(a + 1, b)
                                                  //> sumInts: (a: Int, b: Int)Int
  sumInts(1, 3)                                   //> res0: Int = 6

  def square(x: Int): Int = x * x                 //> square: (x: Int)Int

  def sumSquares(a: Int, b: Int): Int =
    if (a > b) 0 else square(a) + sumSquares(a + 1, b)
                                                  //> sumSquares: (a: Int, b: Int)Int
  sumSquares(1, 3)                                //> res1: Int = 14

  def powerOfTwo(x: Int): Int =
    if (x == 0) 1 else 2 * powerOfTwo(x - 1)      //> powerOfTwo: (x: Int)Int
  def sumPowersOfTwo(a: Int, b: Int): Int =
    if (a > b) 0 else powerOfTwo(a) + sumPowersOfTwo(a + 1, b)
                                                  //> sumPowersOfTwo: (a: Int, b: Int)Int

  sumPowersOfTwo(1, 3)                            //> res2: Int = 14
}
