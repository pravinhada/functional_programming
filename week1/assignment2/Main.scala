package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   * Pascal's Triangle
   */
  def pascal(c: Int, r: Int): Int = if (c == 0 || c == r) 1 else pascal(c - 1, r - 1) + pascal(c, r - 1)

  /**
   * Exercise 2
   * Balancing the Parenthesis
   */
  def balance(chars: List[Char]): Boolean = {

    def bal(chars: List[Char], numOpen: Int): Boolean =
      if (chars.isEmpty) numOpen == 0
      else if (chars.head == '(') bal(chars.tail, numOpen + 1)
      else if (chars.head == ')') numOpen > 0 && bal(chars.tail, numOpen - 1)
      else bal(chars.tail, numOpen)

    bal(chars, 0)
  }

  /**
   * Exercise 3
   * Counting Change
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) 1
    else if (money < 0 || coins.isEmpty) 0
    else countChange(money - coins.head, coins) + countChange(money, coins.tail)
  }
}
