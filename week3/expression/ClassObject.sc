package objsets

object ClassObject {
  println("Object")                               //> Object

  // printing toString
  EmptySet                                        //> res0: objsets.EmptySet.type = _
  val five = new NonEmptySet(5, EmptySet, EmptySet)
                                                  //> five  : objsets.NonEmptySet = _5,_
  val three = new NonEmptySet(3, EmptySet, EmptySet)
                                                  //> three  : objsets.NonEmptySet = _3,_
  // contains
  EmptySet contains 7                             //> res1: Boolean = false

  val seven = new NonEmptySet(7, EmptySet, EmptySet)
                                                  //> seven  : objsets.NonEmptySet = _7,_
  seven contains 7                                //> res2: Boolean = true
  EmptySet contains 1                             //> res3: Boolean = false

  // union
  five union seven                                //> res4: objsets.IntSet = _5,_7,_

  // intersect
  three intersect five                            //> res5: objsets.IntSet = _

  // check isEmpty
  five isEmpty                                    //> res6: Boolean = false

  // union
  val all = three union five union seven          //> all  : objsets.IntSet = _3,_5,_7,_

  // exclude
  all excl 5                                      //> res7: objsets.IntSet = _3,_7,_

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

  def append(other: IntSet): IntSet

  def union(other: IntSet): IntSet

  def intersect(other: IntSet): IntSet

  def excl(x: Int): IntSet

  def isEmpty: Boolean

  override def toString: String
}

// The Empty Set Class
object EmptySet extends IntSet {
  def incl(x: Int): IntSet = new NonEmptySet(x, EmptySet, EmptySet)

  def contains(x: Int): Boolean = false

  def append(other: IntSet): IntSet = other

  def union(other: IntSet): IntSet = other

  def intersect(other: IntSet): IntSet = this

  def excl(x: Int): IntSet = this

  def isEmpty: Boolean = true

  override def toString = "_"
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

  def append(other: IntSet): IntSet = {
    val set = left.append(right.append(other))
    set.incl(elem)
  }

  def union(other: IntSet): IntSet = append(other)

  def intersect(other: IntSet): IntSet = {
    val l = left.intersect(other)
    val r = right.intersect(other)
    val s = l union r
    if (other contains elem) s.incl(elem) else s
  }

  def excl(x: Int): IntSet = {
    if (x < elem) new NonEmptySet(elem, left.excl(x), right)
    else if (x > elem) new NonEmptySet(elem, left, right.excl(x))
    else left append right
  }

  def isEmpty: Boolean = false

  override def toString =
    left.toString() + elem + "," + right.toString()
}