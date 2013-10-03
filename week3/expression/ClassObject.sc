package objsets

object ClassObject {
  println("Object")                               //> Object
  (EmptySet).contains(7)                          //> res0: Boolean = false

  new NonEmptySet(7, EmptySet, EmptySet).contains(7)
                                                  //> res1: Boolean = true
  EmptySet contains 1                             //> res2: Boolean = false
}

/*
abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
}
*/
trait IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
}

// The Empty Set Class
object EmptySet extends IntSet {
  def incl(x: Int): IntSet = new NonEmptySet(x, EmptySet, EmptySet)
  def contains(x: Int): Boolean = false
}

// The Non Empty Set Class
class NonEmptySet(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def incl(x: Int): IntSet =
    if (x < elem) new NonEmptySet(elem, left incl x, right)
    else if (x > elem) new NonEmptySet(elem, left, right incl x)
    else this

  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true
}