package com.despegar.scala.workshop.soluciones.dia2

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

  def forall(f: A => Boolean): Boolean = {
    if (isDefined) f(get) else true
  }

  def exists(f: A => Boolean): Boolean = !forall(_ => !f(get))

  def foreach(f: A => Unit): Unit

  def foldLeft[Z, B >: A](z: Z)(f: (B, Z) => Z): Z

  def contains[B >: A](e: B): Boolean = if (isDefined) e == get else false

  def toList: List[A] = if (isDefined) List(get) else Nil

}

class MySome[A](val get: A) extends MyOptional[A] {
  def isEmpty: Boolean = false

  def getOrElse[B >: A](orElse: => B): B = get

  def map[B](f: A => B): MyOptional[B] = new MySome(f(get))

  def flatMap[B](f: A => MyOptional[B]): MyOptional[B] = f(get)

  def filter(f: A => Boolean): MyOptional[A] = {
    if (f(get)) this else MyNone
  }

  def collect[B](f: PartialFunction[A, B]): MyOptional[B] = {
    if (f.isDefinedAt(get)) new MySome(f(get)) else MyNone
  }

  def foreach(f: A => Unit): Unit = f(get)

  def foldLeft[Z, B >: A](z: Z)(f: (B, Z) => Z): Z = f(get, z)


  override def toString: String = s"MySome($get)"
}

case object MyNone extends MyOptional[Nothing] {
  def get = throw new RuntimeException("MyNone.get")
  def isEmpty = true

  def getOrElse[B >: Nothing](orElse: => B): B = orElse

  def map[B](f: Nothing => B): MyOptional[B] = this

  def flatMap[B](f: Nothing => MyOptional[B]): MyOptional[B] = MyNone

  def filter(f: Nothing => Boolean): MyOptional[Nothing] = this

  def collect[B](f: PartialFunction[Nothing, B]): MyOptional[B] = MyNone

  def foreach(f: Nothing => Unit): Unit = ()

  def foldLeft[Z, B >: Nothing](z: Z)(f: (B, Z) => Z): Z = z

  override def toString: String = s"MyNone"
}

