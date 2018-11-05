package com.despegar.scala.workshop.dia1

object BasicSyntax extends App {

  def abs(n: Int): Int = ???

  // #absDescription(-3) debe imprimir: ABS of -3 is 3
  def absDescription(n: Int): String = ???

  def max(x: Int, y: Int): Int = ???

  // #maxDescription(2, 6) debe imprimir: MAX between 2 and 6 is 6
  def maxDescription(x: Int, y: Int): String = ???

  // Implementar reutilizando max()
  def min(x: Int, y: Int): Int = ???

  // Devolver una función que al pasarle el parámetro de tipo A, aplique la función g
  // y luego con el resultado de esta, aplique la función f
  def compose[A,B,C](f: B => C, g: A => B): A => C = ???

  // Devolver una función que aplique 2 veces la función dada.
  // Ej:
  // > val twiceMultiplyBy2 = twice(x => x * 2)
  // > twiceMultiplyBy2(2)
  // res: 8
  def twice(f: Int => Int): Int => Int = ???

  // Devolver una función que al aplicarle el primer parámetro, me devuelva otra función
  // a la que le pueda aplicar el segundo parámetro, y finalmente aplicar la función inicial
  def curry[A,B,C](f: (A, B) => C): A => (B => C) = ???

  // Es el complemento de curry().
  // Devolver una función que recibe 2 parámetros, y aplique f
  def uncurry[A,B,C](f: A => (B => C)): (A, B) => C = ???
}
