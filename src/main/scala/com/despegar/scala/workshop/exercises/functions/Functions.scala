package com.despegar.scala.workshop.exercises.functions

import scala.annotation.tailrec

object Functions extends App {


  /* 1) Getting started with the syntax */

  // Simple function example
  def abs(n: Int): Int = ???

  // e.g. ABS of -3 is 3
  def absDescription(n: Int): String = s"ABS of $n is ${abs(n)}"

  /* 2) Functional loop */

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
    // Do the same but without mutability
    // Hints! Use recursion
    ???
  }

  // Write a function to compute the nth fibonacci number
  // Remember that the nth fib value is equal to the (nth -1 + nth - 2)
  // e.g. 0, 1, 1, 2, 3, 5, 8, 13, ...
  def fib(n: Int): Int = ???

  /* 3) Stack-safe recursive functions */

  // Euclid's algorithm for compute the greatest common divisor of two numbers
  @tailrec
  def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)


  // Write a stack-safe factorial function
  def factorialSafe(n: Int): Int = {
//    @tailrec
    def go(a: Int, acc: Int): Int = {
      if (a == 0) ???
      else ???
    }
    go(???, acc = 1)
  }

  /* 4) Higher Order Functions */

  // e.g. Factorial of 4 is 24
  def factDescription(n: Int): String = s"Factorial of $n is ${factorial(n)}"

  // Generalize function description using a higher order function
  def operationDescription(name: String, n: Int, f: Int => Int): String = ???
  def factorialDesc(n: Int): String = ???
  def absDesc(n: Int): String = ???

}
