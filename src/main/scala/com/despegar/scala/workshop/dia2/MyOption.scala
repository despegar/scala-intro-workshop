package com.despegar.scala.workshop.dia2

// TRAIT: MyOption[+A]

//  def isEmpty: Boolean
//  def nonEmpty: Boolean = !isEmpty
//  def getOrElse[B >: A](orElse: => B): B
//  def orElse[B >: A](ob: => MyOption[B]): MyOption[B] = ???
//  def isDefined: Boolean = nonEmpty
//
//  def map[B](f: A => B): MyOption[B] = ???
//  def flatMap[B](f: A => MyOption[B]): MyOption[B] = ???
//  def filter(f: A => Boolean): MyOption[A] = ???
//  def forall(f: A => Boolean): Boolean =  ???
//  def exists(f: A => Boolean): Boolean = !forall(x => !f(x))
//  def foreach(f: A => Unit): Unit = ???
//  def foldLeft[Z, B >: A](z: Z)(f: (B, Z) => Z): Z = ???
//  def contains[B >: A](e: B): Boolean = ???
//  def toList: List[A] = ???


// Descomentar y no modificar. Deberia andar con la implementacion de MyOption apropiada
//object MyOption {
//  def apply[A](value: A): MyOption[A] =
//    if (value == null) MyNone
//    else MySome(value)
//
//  def empty[A]: MyOption[A] = MyNone
//}

// debe extender de MyOption
// case class: MySome[A](value: A)
//  def isEmpty: Boolean = ???
//  def getOrElse[B >: A](orElse: => B): B = ???

// debe extender de MyOption
// case object MyNone
//  def isEmpty: Boolean = ???
//  def getOrElse[B >: Nothing](orElse: => B): B = ???
