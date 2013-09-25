package funsets

/**
 * Higher Order Functions for some mathmatical expression
 *
 */

object functions1 {

  def id(x: Int): Int = x                         //> id: (x: Int)Int

  def square(x: Int): Int = x * x                 //> square: (x: Int)Int

  def powerOfTwo(x: Int): Int =
    if (x == 0) 1 else 2 * powerOfTwo(x - 1)      //> powerOfTwo: (x: Int)Int

  def sum(f: Int => Int, a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sum(f, a + 1, b)     //> sum: (f: Int => Int, a: Int, b: Int)Int

  def sumInts(a: Int, b: Int): Int = sum(id, a, b)//> sumInts: (a: Int, b: Int)Int

  sumInts(1, 3)                                   //> res0: Int = 6

  def sumSquares(a: Int, b: Int): Int = sum(square, a, b)
                                                  //> sumSquares: (a: Int, b: Int)Int
  sumSquares(1, 3)                                //> res1: Int = 14

  def sumPowersOfTwo(a: Int, b: Int): Int = sum(powerOfTwo, a, b)
                                                  //> sumPowersOfTwo: (a: Int, b: Int)Int

  sumPowersOfTwo(1, 3)                            //> res2: Int = 14

}