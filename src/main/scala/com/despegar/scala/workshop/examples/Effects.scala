package com.despegar.scala.workshop.examples

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Credits to Luigi Antonini
  * @see https://medium.com/zendesk-engineering/dont-fear-the-monad-f424260f29f6
  */
object Effects {

  /**
    * Null check
    */
  def printResult(result: Result): String = {
    if (result != null)
      result.toString
    else
      "Result is null"
  }

  /**
    * Error handling by Exception
    */
  def divide(dividend: Int, divisor: Int):Int = {
    if (divisor == 0)
      throw new RuntimeException("Divisor can't be 0")
    dividend / divisor
  }

  def usingDivision: Unit = {
    try {
      val result = divide(10, 0)
      println(s"The result is $result")
    } catch {
      case exc: Throwable =>
        println(s"Exception: ${exc.getMessage}")
    }
  }


  /**
    * Asynchronous computation
    */
  val thread: Thread = new Thread() {
    override def run(): Unit = {
      System.out.println("Thread Running")
    }
  }
  thread.start()

  /**
    * How many times do we need to do this null check?
    *
    * Unfortunately this is a very good way to introduce bugs,
    * for instance in the first example is very easy to forget
    * to check the value in every place where it is used,
    * you need to rely on the documentation to know if a value
    * can be null or not, and you alway end up with
    * NullPointerException.
    *
    * Now in Scala and many other languages we have a type system,
    * so why not use it to our advantage?
    */

  /**
    * This behaviours have already been encoded with a type:
    * Option and Future
    */

  /**
    * Null-check via Option.
    *
    * Now, when you call the method printResult you know already that
    * foo is an optional value, you’ll have to handle the None case
    * and you don’t have to rely on the documentation, and if you
    * forget to make it an Option the compiler will remind you,
    * and if you pass foo to another method, you don’t have to check
    * if the method can handle the None case or not, you’ll know it
    * from its definition.
    */
  def printResult(foo: Option[Result]): String = {
    foo.map { fooValue =>
      fooValue.value
    } getOrElse "foo is None"
  }

  /**
    * Handling Error via Either
    *
    * Now, when you call the method divide you know already that
    * it may can compute the division or just return an exception.
    *
    */
  def divide2(dividend: Int, divisor: Int): Either[RuntimeException, Int] = {
    if (divisor == 0)
      Left(new RuntimeException("Divisor can't be 0"))
    else
      Right(dividend / divisor)
  }

  def usingDivision2: Unit = {
    divide2(10, 0)
      .map(result => println(s"The result is $result"))
      .left.map(exc => println(s"Exception: ${exc.getMessage}"))
  }

  /**
    * Asynchronous computation via Future
    *
    * All the complicated logic needed to wait for the value to be
    * ready, is handled by the future, so if you want to get the
    * result of the operation you don’t have to deal with that.
    */
  val running = Future {
    println("Thread Running")
  }

  case class Result(value: String)
}
