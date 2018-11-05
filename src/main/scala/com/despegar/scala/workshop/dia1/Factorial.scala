package com.despegar.scala.workshop.dia1

// Implementar con un estilo funcional (no usar variables mutables)
// Ej: 6! = 1 x 2 x 3 x 4 x 5 x 6 = 720
/**
  * La función Factorial de un entero positivo n,
  * es el producto entre todos los enteros que hay entre 1 y n. Ej:
  *
  * 6! = 1 * 2 * 3 * 4 * 5 * 6 = 720
  *
  * Implementarlo de forma funcional, sin usar variables mutables.
  */
object Factorial extends App {

  /**
    * ¿Qué pasa con valores grandes, como por ej. `calculateInt(1000)`?
    * @param num
    * @return
    */
  def calculateInt(num: Int): Int = ???

  /**
    * Implementar, de tal modo que se puedan pasar números grandes, por ejemplo 1000
    *
    * Pueden cambiar los tipos de datos
    */
  def calculateForBigNumbers(num: Int): Int = ???

  /**
    * Re implementar, tal que sea stack-safe
    *
    * Pueden cambiar los tipos de datos
    */
  def calculateRec(num: BigInt): BigInt = {
//    @scala.annotation.tailrec
    def accum(acc: BigInt, number: BigInt): BigInt = number match {
      case n if n < 0 => throw new IllegalArgumentException("Only positive integers accepted")
      case n if n == 0 => ???
      case n => ???
    }

    accum(???, number = num)
  }
}
