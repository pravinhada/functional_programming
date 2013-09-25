package funsets

/**
 * Rational Number Example using Class
 *
 */
object RationalNumber {
  val x = new Rational(1, 3)                      //> x  : funsets.Rational = 1/3
  x.numer                                         //> res0: Int = 1
  x.denom                                         //> res1: Int = 3
  val y = new Rational(2, 3)                      //> y  : funsets.Rational = 2/3
  val z = new Rational(4, 3)                      //> z  : funsets.Rational = 4/3

  x.add(y)                                        //> res2: funsets.Rational = 1/1
  x.neg(y)                                        //> res3: funsets.Rational = 1/-3
  x.less(y)                                       //> res4: Boolean = true
  x.max(y)                                        //> res5: funsets.Rational = 2/3

  x.add(y).mul(z)                                 //> res6: funsets.Rational = 4/3

  new Rational(1, 2).less(new Rational(2, 3))     //> res7: Boolean = true
}

// Rational class
class Rational(x: Int, y: Int) {

  require(y > 0, "denominator must be positive")

  // constructor
  def this(x: Int) = this(x, 1)

  // This is to reduce to simplest form
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
  val numer = x / gcd(x, y)
  val denom = y / gcd(x, y)

  // addition method
  def add(r: Rational) =
    new Rational(numer * r.denom + r.numer * denom, denom * r.denom)

  // multiplication method
  def mul(r: Rational) =
    new Rational(numer * r.numer, denom * r.denom)

  // substraction
  def neg(r: Rational) =
    new Rational(numer * r.denom - r.numer * denom, denom * r.denom)

  // checkng less than
  def less(that: Rational) =
    this.numer * that.denom < that.numer * this.denom

  // checking for max
  def max(that: Rational) =
    if (this.less(that)) that else this

  override def toString = numer + "/" + denom
}