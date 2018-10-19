package com.despegar.scala.workshop.answers.functions

import scala.annotation.tailrec

object Functions extends App {

  // Simple function example
  def abs(n: Int): Int = if (n < 0) -n else n

  // e.g. ABS(-3) = 3
  def absDescription(n: Int): String = s"ABS($n) = ${abs(n)}"

  // Loop in imperative style:
  def impFactorial(a: Int): Int = {
    var result = 1
    var i = 1
    while (i <= a) {
      result = result * i
      i += 1
    }
    result
  }

  // Functional style:
  def factorial(a: Int): Int = {
    if (a == 0) 1
    else a * factorial(a - 1)
  }

  // Write a function to compute the nth fibonacci number
  def fib(n: Int): Int = {
    if (n <= 1) 0
    else if (n == 2) 1
    else fib(n - 1) + fib(n - 2)
  }

  // Write a stack-safe factorial function
  def factorialSafe(n: Int): Int = {
    @tailrec
    def go(a: Int, acc: Int): Int = {
      if (a == 0) acc
      else go(a - 1, acc * a)
    }
    go(n, acc = 1)
  }

  // e.g. Factorial of 4 is 24
  def factDescription(n: Int): String = s"Factorial of $n is ${factorial(n)}"

  // Generalize function description using a higher order function
  def operationDescription(name: String, n: Int, f: Int => Int): String = s"$name of $n is ${f(n)}"
  def factorialDesc(n: Int): String = operationDescription("Factorial", n, factorialSafe)
  def absDesc(n: Int): String = operationDescription("ABS", n, abs)

}
