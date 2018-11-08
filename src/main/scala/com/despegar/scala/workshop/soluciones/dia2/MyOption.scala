package com.despegar.scala.workshop.soluciones.dia2

sealed trait MyOptionSol[+A] {
  def isEmpty: Boolean
  def nonEmpty: Boolean = !isEmpty
  def getOrElse[B >: A](orElse: => B): B
  def orElse[B >: A](ob: => MyOptionSol[B]): MyOptionSol[B] = this match {
    case MyNoneSol => ob
    case _         => this
  }

  def isDefined: Boolean = nonEmpty

  def map[B](f: A => B): MyOptionSol[B] = this match {
    case MyNoneSol        => MyNoneSol
    case MySomeSol(value) => MyOptionSol(f(value))
  }

  def flatMap[B](f: A => MyOptionSol[B]): MyOptionSol[B] = this match {
    case MyNoneSol        => MyNoneSol
    case MySomeSol(value) => f(value)
  }

  def filter(f: A => Boolean): MyOptionSol[A] = this match {
    case MySomeSol(value) if f(value) => MySomeSol(value)
    case _ => MyNoneSol
  }

  def forall(f: A => Boolean): Boolean =  this match {
    case MySomeSol(value) => f(value)
    case _ => true
  }

  def exists(f: A => Boolean): Boolean = !forall(x => !f(x))

  def foreach(f: A => Unit): Unit = this match {
    case MySomeSol(value) => f(value)
    case _ => ()
  }

  def foldLeft[Z, B >: A](z: Z)(f: (B, Z) => Z): Z = this match {
    case MySomeSol(value) => f(value, z)
    case _ => z
  }

  def contains[B >: A](e: B): Boolean = this match {
    case MySomeSol(value) if e == value => true
    case _ => false
  }

  def toList: List[A] = this match {
    case MySomeSol(value) => List(value)
    case _ => Nil
  }
}

object MyOptionSol {
  def apply[A](value: A): MyOptionSol[A] =
    if (value == null) MyNoneSol
    else MySomeSol(value)

  def empty[A]: MyOptionSol[A] = MyNoneSol
}

final case class MySomeSol[A](value: A) extends MyOptionSol[A] {
  override def isEmpty: Boolean = false
  override def getOrElse[B >: A](orElse: => B): B = value
}

case object MyNoneSol extends MyOptionSol[Nothing] {
  override def isEmpty: Boolean = true
  override def getOrElse[B >: Nothing](orElse: => B): B = orElse
}
