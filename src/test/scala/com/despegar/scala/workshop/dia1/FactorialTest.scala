package com.despegar.scala.workshop.dia1

import org.scalatest._

class FactorialTest extends FlatSpec with Matchers {

  import Factorial._

  "Factorial of 0" should "be 1" in {
    calculateInt(0) should be (1)
  }

  "Factorial of a number n" should "be the multiplication of n by the factorial of n - 1, being n > 0" in {
    calculateInt(1) should be (1)
    calculateInt(5) should be (120)
    // calculateInt(50) won't work
    // calculateInt(25000) won't work
  }

  it should "throw IllegalArgumentException for negative numbers" in {
    a [IllegalArgumentException] should be thrownBy {
      calculateInt(-1)
    }
  }
}