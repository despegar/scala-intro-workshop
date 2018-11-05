package com.despegar.scala.workshop.dia1

import scala.annotation.tailrec

trait ListUtils {

  def length[A](xs: List[A]): Int = xs match {
    case Nil          => 0
    case head :: tail => 1 + length(tail)
  }

  def last[A](l: List[A]): A = l match {
    case Nil      => throw new RuntimeException("Bum!")
    case x :: Nil => x
    case _ :: xs  => last(xs)
  }

  def init[A](l: List[A]): List[A] = l match {
    case Nil      => throw new RuntimeException("Bum!")
    case x :: Nil => Nil
    case x :: xs  => x :: init(xs)
  }

  def isEmpty[A](l: List[A]) : Boolean = ???

  def dropWhile[A](xs: List[A], f: A => Boolean): List[A] = ???

  def concat[A](xs: List[A], ys: List[A]): List[A] = ???

  /**
    * Puedes reutilizar concat(xs, ys) implementado anteriormente
    */
  def flatten[B](xs: List[List[B]]): List[B] = ???

  /**
    * Puedes reutilizar concat(xs, ys) implementado anteriormente
    */
  def reverse[A](l: List[A]): List[A] = ???

  // Map

  def scaleList(xs: List[BigDecimal], factor: BigDecimal): List[BigDecimal] = xs match {
    case Nil => xs
    case y :: ys => y * factor :: scaleList(ys, factor)
  }

  def ages(people: List[Person]): List[Int] = people match {
    case Nil => Nil
    case x :: xs => x.age :: ages(xs)
  }

  def map[A, B](xs: List[A])(f : A => B): List[B] = ???

  /**
    * Puedes reutilizar concat(xs, ys) implementado anteriormente
    */
  def flatMap[A, B](xs: List[A])(f : A => List[B]): List[B] = ???

  def scaleList_viaMap(xs: List[BigDecimal], factor: BigDecimal): List[BigDecimal] = ???

  def ages_viaMap(people: List[Person]): List[Int] = ???

  // Filter

  def posElems(xs: List[Int]): List[Int] = xs match {
    case Nil => xs
    case y :: ys => if (y > 0) y :: posElems(ys) else posElems(ys)
  }

  def adults(persons: List[Person]): List[Person] = persons match {
    case Nil     => Nil
    case x :: xs => if (x.age >= 18) x :: adults(xs) else adults(xs)
  }

  def filter[A](xs: List[A])(f: A => Boolean): List[A] = ???

  def posElems_viaFilter(xs: List[Int]): List[Int] = ???

  def adults_viaFilter(persons: List[Person]): List[Person] = ???

  // FoldRight

  def sum(ints: List[Int]): Int = ints match {
    case Nil     => 0
    case x :: xs => x + sum(xs)
  }

  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = ???

  def sum_viaFoldRight(ints: List[Int]): Int = ???

  def length_viaFoldRight[A](l: List[A]): Int = ???

  def concat_viaFoldRight[A](l1: List[A], l2: List[A]): List[A] = ???

  def flatten_viaFoldRight[B](xs: List[List[B]]): List[B] = ???

  def map_viaFoldRight[A, B](xs: List[A])(f: A => B): List[B] = ???

  // FoldLeft

  // Recuerda aplicar la técnica para transformar la función foldRight en tail-recursive
  def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B = ???

  def sum_viaFoldLeft(ints: List[Int]): Int = ???

  def length_viaFoldLeft[A](l: List[A]): Int = ???

  def reverse_viaFoldLeft[A](l: List[A]): List[A] = ???

  // Difícil!!
  def foldRight_viaFoldLeft[A,B](as: List[A], z: B)(f: (A, B) => B): B = ???

  // Find

  def find[A](l: List[A], f: A => Boolean): A = ???
}

object ListUtils extends ListUtils

case class Person(age: Int)
