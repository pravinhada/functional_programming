package funsets

/**
 * Using Anonymous Function
 *
 */
object functions2 {

  def powerOfTwo(x: Int): Int =
    if (x == 0) 1 else 2 * powerOfTwo(x - 1)      //> powerOfTwo: (x: Int)Int

  def sum(f: Int => Int, a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sum(f, a + 1, b)     //> sum: (f: Int => Int, a: Int, b: Int)Int

  // Anonymous function (x: Int) => x
  def sumInts(a: Int, b: Int): Int =
    sum((x: Int) => x, a, b)                      //> sumInts: (a: Int, b: Int)Int

  sumInts(1, 3)                                   //> res0: Int = 6

  // Anonymous function (x: Int) => x * x
  def sumSquares(a: Int, b: Int): Int =
    sum((x: Int) => x * x, a, b)                  //> sumSquares: (a: Int, b: Int)Int

  sumSquares(1, 3)                                //> res1: Int = 14

  def sumPowersOfTwo(a: Int, b: Int): Int =
    sum(powerOfTwo, a, b)                         //> sumPowersOfTwo: (a: Int, b: Int)Int

  sumPowersOfTwo(1, 3)                            //> res2: Int = 14
}