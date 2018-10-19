package com.despegar.scala.workshop.examples

object PlayGround extends App {

  // Loop in functional programming:
  def impFactorial(a: Int): Int = {
    var result = 1
    var i = 1
    while (i <= a) {
      result = result * i
      i += 1
    }
    result
  }

  // Do the same but without mutability
  def recFactorial(a: Int): Int = {
    if (a < 1) 1 else a * recFactorial(a - 1)
  }



  // intro to fold()

  // Take the sum of the integers between a and b:
  def sumInts(a: Int, b: Int): Int = {
    if (a > b) 0 else a + sumInts(a + 1, b)
  }

  sumInts(1, 3)
  if (1 > 3) 0 else 1 + sumInts(1 + 1, 3)
  if (false) 0 else 1 + sumInts(1 + 1, 3)
  1 + sumInts(1 + 1, 3)
  1 + sumInts(2, 3)
  1 + (2 + sumInts(3, 3))
  1 + (2 + (3 + sumInts(4, 5)))
  1 + (2 + (3 + (4 + sumInts(5, 5))))
  1 + (2 + (3 + (4 + (5 + sumInts(6, 5)))))
  1 + (2 + (3 + (4 + (5 + 0))))
  // Take the sum of the cubes of all the integers between a and b:
  def sumCubes(a: Int, b: Int): Int = {
    if (a > b) 0
    else (a * a * a) + sumCubes(a + 1, b)
  }

  def sumResults(a: Int, b: Int, resultFunc: Int => Int): Int = {
    if (a > b) 0
    else resultFunc(a) + sumResults(a + 1, b, resultFunc)
  }

  def sumInts2(a: Int, b: Int): Int = sumResults(a, b, x => x)
  def sumCubes2(a: Int, b: Int): Int = sumResults(a, b, x => x * x * x)

  def sumFactorial(a: Int, b: Int): Int = sumResults(a, b, recFactorial)

  println(s"Result: ${1*2*3*4*5*6*7*8*9*10*11*12*13*14*15*16*17*18*19*20*21*22*23*24*25*26*27*28*29*30*31*32*33*34*35*36*37*38*39*40}")
}
