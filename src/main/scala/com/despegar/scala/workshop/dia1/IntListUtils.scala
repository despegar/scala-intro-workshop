package com.despegar.scala.workshop.dia1

object IntListUtils {

  def isEmpty(xs: Seq[Int]) : Boolean = ???

  def find(xs: Seq[Int], p : Int => Boolean) : Option[Int] = ???

  def foreach[U](xs: Seq[Int], f : Int => U) : Unit = ???

  def map[B](xs: Seq[Int], f : Int => B): Seq[B] = ???

  def filter(xs: Seq[Int], p : Int => Boolean): Seq[Int] = ???

  def take(xs: Seq[Int], n : Int) : Seq[Int] = ???

  def drop(xs: Seq[Int], n : Int) : Seq[Int] = ???
}


