package objsets

object poly {
  println("Polymorphismt")                        //> Polymorphismt

  def singleton[T](elem: T) = new Cons1[T](elem, new Nil1[T])
                                                  //> singleton: [T](elem: T)objsets.Cons1[T]
  singleton(1)                                    //> res0: objsets.Cons1[Int] = objsets.Cons1@16bf0aa
  singleton(true)                                 //> res1: objsets.Cons1[Boolean] = objsets.Cons1@18532dc

  def nth[T](n: Int, xs: List[T]): T =
    if (xs.isEmpty) throw new IndexOutOfBoundsException
    else if (n == 0) xs.head
    else nth(n - 1, xs.tail)                      //> nth: [T](n: Int, xs: objsets.List[T])T

  val list = new Cons1(1, new Cons1(2, new Cons1(3, new Cons1(4, new Nil1))))
                                                  //> list  : objsets.Cons1[Int] = objsets.Cons1@1ce1387
  nth(2, list)                                    //> res2: Int = 3
  nth(0, list)                                    //> res3: Int = 1

	list.head                                 //> res4: Int = 1
	list.tail                                 //> res5: objsets.List[Int] = objsets.Cons1@640b25
	
  // print using for each loop
  // and println(_) as predicate
  list.foreach(println(_))                        //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
}

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  def foreach(f: T => Unit): Unit =
    if (!isEmpty) {
      f(head)
      tail.foreach(f)
    }
}

class Cons1[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}

class Nil1[T] extends List[T] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}