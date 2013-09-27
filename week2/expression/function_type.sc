package funsets

object function_type {
  (x: Int) => true                                //> res0: Int => Boolean = <function1>
  (x: Int) => false                               //> res1: Int => Boolean = <function1>
  (x: Int) => x == 2                              //> res2: Int => Boolean = <function1>
  (x: Int) => x == 10                             //> res3: Int => Boolean = <function1>
  (x: Int) => x == 2 || x == 10                   //> res4: Int => Boolean = <function1>
  (x: Int) => x % 2 == 0                          //> res5: Int => Boolean = <function1>
}