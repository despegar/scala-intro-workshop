package com.despegar.scala.workshop.soluciones.dia1

import scala.annotation.tailrec

object Factorial {

  def calculateInt(num: Int): Int = num match {
    case n if n < 0 => throw new IllegalArgumentException("Only positive integers accepted")
    case n if n == 0 => 1
    case n => n * calculateInt(n - 1)
  }

  def calculateForBigNumbers(num: BigInt): BigInt = num match {
    case n if n < 0 => throw new IllegalArgumentException("Only positive integers accepted")
    case n if n == 0 => BigInt(1)
    case n => n * calculateForBigNumbers(n - 1)
  }

  def calculateRec(num: BigInt): BigInt = {
    @tailrec
    def accum(acc: BigInt, number: BigInt): BigInt = number match {
      case n if n < 0 => throw new IllegalArgumentException("Only positive integers accepted")
      case n if n == 0 => acc
      case n => accum(acc * n, n - 1)
    }

    accum(BigInt(1), num)
  }

}
