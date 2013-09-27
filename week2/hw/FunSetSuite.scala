package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  test("it should not contain -100") {
    assert(!contains({ x: Int => x > 0 }, -100))
  }
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
    val positiveNumber = union(union(singletonSet(10), singletonSet(100)), singletonSet(300))
    val negativeNumber = union(union(singletonSet(-10), singletonSet(-100)), singletonSet(-300))
    val positiveAndNegativeNumbers = union(positiveNumber, negativeNumber)
    val evenNumbers = union(singletonSet(4), singletonSet(6))
    val oddNumbers = union(singletonSet(3), singletonSet(9))
    val evenAndOddNumbers = union(evenNumbers, oddNumbers)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
      assert(!contains(s1, 2), "Singleton")
    }
  }

  /**
   * Union Test
   */
  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  /**
   * Intersect Test
   */
  test("intersect contains all common elements") {
    new TestSets {
      val su = union(s1, s2)
      val si = intersect(su, s2)
      assert(!contains(si, 1), "doesn't contain 1 in intersect")
      assert(contains(si, 2), "contain 2 in intersect")
    }
  }

  /**
   * Diff test
   *
   */
  test("diff contains all elements from first set not in second set") {
    new TestSets {
      val su = union(s1, s2)
      val sd = diff(su, s3)
      assert(contains(sd, 1), "contain 1 after diff")
      assert(contains(sd, 2), "contain 2 after diff")
      assert(!contains(sd, 3), "doesn't contain 3 after diff")
    }
  }

  /**
   * Filter test
   */
  test("filter returns subset of set which hold parameter p") {
    new TestSets {
      val su = union(s1, s2)
      val suu = union(su, s3)
      // parameter for all set greater than 1
      val sf = filter(suu, { x: Int => x > 1 })

      val suu1 = union(suu, s4)
      // for all set greater than 1 and less than 4
      val sf1 = filter(suu1, { x: Int => x > 1 && x < 4 })
      assert(!contains(sf, 1), "shouldn't contain 1 after filter")
      assert(contains(sf, 2), "should contain 2 after filter")
      assert(contains(sf, 3), "should contain 3 after filter")

      assert(contains(sf1, 2), "should contain 2 after filter")
      assert(contains(sf1, 3), "should contain 3 after filter")
      assert(!contains(sf1, 4), "shouldn't contain 4 after filter")
    }
  }

  /**
   * Test forall
   */
  test("test forall bounded integer x > 0  or x < 0") {
    new TestSets {
      assert(forall(positiveNumber, { x: Int => x > 0 }))
      assert(forall(negativeNumber, { x: Int => x < 0 }))
      assert(forall(evenNumbers, { x: Int => (x % 2) == 0 }))
      assert(forall(oddNumbers, { x: Int => (x % 2) != 0 }))
    }
  }

  test("exists function") {
    new TestSets {
      assert(exists(positiveAndNegativeNumbers, { elem: Int => elem > 0 }))
      assert(exists(evenAndOddNumbers, { elem: Int => (elem % 2) == 0 }))
    }
  }

  test("map function") {
    new TestSets {
      val mapEvenSetToOdd = map(evenNumbers, { elem: Int => elem + 1 })
      printSet(mapEvenSetToOdd)
      assert(contains(mapEvenSetToOdd, 5) && contains(mapEvenSetToOdd, 7))
      val mapOddSetToEven = map(oddNumbers, { elem: Int => elem * 2 })
      assert(forall(mapOddSetToEven, { elem: Int => (elem % 2) == 0 }))
    }
  }
}
