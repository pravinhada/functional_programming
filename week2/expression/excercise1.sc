package funsets

// Exercises

object excercise1 {

  // 1. Write a function product that computes the product of the values of functions at points over a given range.

  def product(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 1
    else f(a) * product(f)(a + 1, b)              //> product: (f: Int => Int)(a: Int, b: Int)Int

  product(x => x * x)(1, 3)                       //> res0: Int = 36

  // 3. Generalize both sum and product
  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int =
    if (a > b) zero
    else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))
                                                  //> mapReduce: (f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b:
                                                  //|  Int)Int
  def product1(f: Int => Int)(a: Int, b: Int): Int =
    mapReduce(f, (x, y) => x * y, 1)(a, b)        //> product1: (f: Int => Int)(a: Int, b: Int)Int

  // 2. Factorial
  def fact(n: Int) = product(x => x)(1, n)        //> fact: (n: Int)Int

  fact(5)                                         //> res1: Int = 120

  // Factorial using both sum and product
  def fact1(n: Int) = product1(x => x)(1, n)      //> fact1: (n: Int)Int

  fact1(5)                                        //> res2: Int = 120
}