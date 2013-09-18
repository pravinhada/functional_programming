package greeter

object Expression {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  87 + 145                                        //> res0: Int(232) = 232

  5 + 2 * 3                                       //> res1: Int = 11

  "Hello" + " World!"                             //> res2: String("Hello World!") = Hello World!

  def scale = 5                                   //> scale: => Int

  7 * scale                                       //> res3: Int = 35

  def pi = 3.141592653                            //> pi: => Double

  def radius = 10                                 //> radius: => Int

  2 * pi * radius                                 //> res4: Double = 62.83185306

  def square(x: Double) = x * x                   //> square: (x: Double)Double

  square(2)                                       //> res5: Double = 4.0

  square(5 + 3)                                   //> res6: Double = 64.0

  square(square(4))                               //> res7: Double = 256.0

  def sumOfSquares(x: Double, y: Double) = square(x) + square(y)
                                                  //> sumOfSquares: (x: Double, y: Double)Double

  sumOfSquares(3, 2 + 2)                          //> res8: Double = 25.0

  def loop: Int = loop                            //> loop: => Int

  // pass by value and pass by name
  def constOne(x: Int, y: => Int) = 1             //> constOne: (x: Int, y: => Int)Int

  constOne(1, loop)                               //> res9: Int = 1

  // this will loop infinitly
  //constOne(loop, 2)

  def abs(x: Double) = if (x >= 0) x else -x      //> abs: (x: Double)Double

  // Square root by Newton's method using nested function
  def sqrt(x: Double) = {
    def sqrtIter(guess: Double, x: Double): Double = if (isGoodEnough(guess, x)) guess else sqrtIter(improve(guess, x), x)
    def isGoodEnough(guess: Double, x: Double) = abs(square(guess) - x) < 0.001
    def improve(guess: Double, x: Double) = (guess + x / guess) / 2
    sqrtIter(1.0, x)
  }                                               //> sqrt: (x: Double)Double

  sqrt(2)                                         //> res10: Double = 1.4142156862745097

  // Greatest Common Factor
  def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
                                                  //> gcd: (a: Int, b: Int)Int

  gcd(14, 21)                                     //> res11: Int = 7

  def factorial(n: Int, accumulator: Long = 1): Int = if (n == 0) 1 else n * factorial(n - 1)
                                                  //> factorial: (n: Int, accumulator: Long)Int

  factorial(5)                                    //> res12: Int = 120

}