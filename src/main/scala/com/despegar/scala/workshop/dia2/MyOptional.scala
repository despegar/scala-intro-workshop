package com.despegar.scala.workshop.dia2

trait MyOptional[+A] {
  def get: A
  def getOrElse[B >: A](orElse: => B): B
  def isEmpty: Boolean
  def nonEmpty: Boolean = !isEmpty
  def isDefined: Boolean = nonEmpty

  def map[B](f: A => B): MyOptional[B]

  def flatMap[B](f: A => MyOptional[B]): MyOptional[B]

  def filter(f: A => Boolean): MyOptional[A]

  def collect[B](f: PartialFunction[A, B]): MyOptional[B]

  def forall(f: A => Boolean): Boolean = if (isDefined) f(get) else true

  def exists(f: A => Boolean): Boolean = !forall(_ => !f(get))

  def foreach(f: A => Unit): Unit

  def foldLeft[Z, B >: A](z: Z)(f: (B, Z) => Z): Z

  def contains[B >: A](e: B): Boolean = if (isDefined) e == get else false

  def toList: List[A] = if (isDefined) List(get) else Nil

}

abstract class MySome[A](val get: A) extends MyOptional[A] {
  // TODO - Quitar el abstract e implementar los metodos
}

abstract class MyNone extends MyOptional[Nothing] {
  // TODO - Reemplazar "abstract class" por "case object"
}

