package com.despegar.scala.workshop.dia1

import org.scalatest._

class FactorialForBigNumbersTest extends FlatSpec with Matchers {

  import Factorial._

  "Factorial of 0" should "be 1" in {
    calculateForBigNumbers(0) should be (1)
  }

  "Factorial of a number n" should "be the multiplication of n by the factorial of n - 1, being n > 0" in {
    calculateForBigNumbers(1) should be (1)
    calculateForBigNumbers(5) should be (120)
    calculateForBigNumbers(50) should be (BigInt("30414093201713378043612608166064768844377641568960512000000000000"))
    // calculateForBigNumbers(25000) won't work
  }

  it should "throw IllegalArgumentException for negative numbers" in {
    a [IllegalArgumentException] should be thrownBy {
      calculateForBigNumbers(-1)
    }
  }
}