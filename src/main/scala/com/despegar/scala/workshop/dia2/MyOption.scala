package com.despegar.scala.workshop.dia2

sealed trait MyOption[+A] {
  def isEmpty: Boolean
  def nonEmpty: Boolean = !isEmpty
  def getOrElse[B >: A](orElse: => B): B
  def orElse[B >: A](ob: => B): B
  def isDefined: Boolean = nonEmpty

  def map[B](f: A => B): MyOption[B] = ???
  def flatMap[B](f: A => MyOption[B]): MyOption[B] = ???
  def filter(f: A => Boolean): MyOption[A] = ???
  def forall(f: A => Boolean): Boolean =  ???
  def exists(f: A => Boolean): Boolean = !forall(x => !f(x))
  def foreach(f: A => Unit): Unit = ???
  def foldLeft[Z, B >: A](z: Z)(f: (B, Z) => Z): Z = ???
  def contains[B >: A](e: B): Boolean = ???
  def toList: List[A] = ???
}

object MyOption {
  def apply[A](value: A): MyOption[A] =
    if (value == null) MyNone
    else MySome(value)
}

final case class MySome[A](value: A) extends MyOption[A] {
  override def isEmpty: Boolean = ???
  override def getOrElse[B >: A](orElse: => B): B = ???
  override def orElse[B >: A](ob: => B): B = ???
}

case object MyNone extends MyOption[Nothing] {
  override def isEmpty: Boolean = ???
  override def getOrElse[B >: Nothing](orElse: => B): B = ???
  override def orElse[B >: Nothing](ob: => B): B = ???
}

