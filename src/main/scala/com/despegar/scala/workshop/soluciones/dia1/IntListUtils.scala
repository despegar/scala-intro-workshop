package com.despegar.scala.workshop.soluciones.dia1

object IntListUtils {

  def isEmpty(xs: Seq[Int]) : Boolean = xs match {
    case Nil => true
    case _ => false
  }

  def find(xs: Seq[Int], p : Int => Boolean) : Option[Int] = xs match {
    case Nil => None
    case x :: _ if p(x) => Some(x)
    case _ :: tail => find(tail, p)
  }

  def foreach[U](xs: Seq[Int], f : Int => U) : Unit = xs match {
    case Nil => Unit
    case x :: tail =>
      f(x)
      foreach(tail, f)
  }

  def map[B](xs: Seq[Int], f : Int => B): Seq[B] = xs match {
    case Nil => Nil
    case x :: tail => f(x) +: map(tail, f)
  }

  def filter(xs: Seq[Int], p : Int => Boolean): Seq[Int] = xs match {
    case Nil => Nil
    case x :: tail if p(x) => x +: filter(tail, p)
    case _ :: tail => filter(tail, p)
  }

  def take(xs: Seq[Int], n : Int) : Seq[Int] = xs match {
    case Nil => Nil
    case _ if n == 0  => Nil
    case x :: tail => x +: take(tail, n - 1)
  }

  def drop(xs: Seq[Int], n : Int) : Seq[Int] = xs match {
    case Nil => Nil
    case rest if n == 0 => rest
    case _ :: tail => drop(tail, n - 1)
  }
}
