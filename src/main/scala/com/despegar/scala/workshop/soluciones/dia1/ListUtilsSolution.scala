package com.despegar.scala.workshop.soluciones.dia1

import com.despegar.scala.workshop.dia1.{ListUtils, Person}

import scala.annotation.tailrec

object ListUtilsSolution extends ListUtils {

  override def length[A](xs: List[A]): Int = xs match {
    case Nil          => 0
    case head :: tail => 1 + length(tail)
  }

  override def last[A](l: List[A]): A = l match {
    case Nil      => throw new RuntimeException("Bum!")
    case x :: Nil => x
    case _ :: xs  => last(xs)
  }

  override def init[A](l: List[A]): List[A] = l match {
    case Nil      => throw new RuntimeException("Bum!")
    case x :: Nil => Nil
    case x :: xs  => x :: init(xs)
  }

  override def isEmpty[A](l: List[A]) : Boolean = l match {
    case Nil => true
    case _   => false
  }

  override def dropWhile[A](xs: List[A], f: A => Boolean): List[A] = xs match {
    case Nil                     => Nil
    case head :: tail if f(head) => dropWhile(tail, f)
    case _                       => xs
  }

  override def concat[A](xs: List[A], ys: List[A]): List[A] = xs match {
    case Nil          => ys
    case head :: tail => head :: concat(tail, ys)
  }

  /**
    * Puedes reutilizar ++(xs, ys) implementado anteriormente
    */
  override def flatten[B](xs: List[List[B]]): List[B] = xs match {
    case Nil          => Nil
    case head :: tail => concat(head, flatten(tail))
  }

  /**
    * Puedes reutilizar ++(xs, ys) implementado anteriormente
    */
  override def reverse[A](l: List[A]): List[A] = l match {
    case Nil     => Nil
    case x :: xs => concat(reverse(xs), x :: Nil)
  }

  // Map

  override def scaleList(xs: List[BigDecimal], factor: BigDecimal): List[BigDecimal] = xs match {
    case Nil => xs
    case y :: ys => y * factor :: scaleList(ys, factor)
  }

  override def ages(people: List[Person]): List[Int] = people match {
    case Nil => Nil
    case x :: xs => x.age :: ages(xs)
  }

  override def map[A, B](xs: List[A])(f : A => B): List[B] = xs match {
    case Nil => Nil
    case head :: tail => f(head) :: map(tail)(f)
  }

  override def flatMap[A, B](xs: List[A])(f : A => List[B]): List[B] = xs match {
    case Nil => Nil
    case head :: tail => concat(f(head), flatMap(tail)(f))
  }

  override def scaleList_viaMap(xs: List[BigDecimal], factor: BigDecimal): List[BigDecimal] = map(xs)((x: BigDecimal) => x * factor)

  override def ages_viaMap(people: List[Person]): List[Int] = map(people)(_.age)

  // Filter

  override def posElems(xs: List[Int]): List[Int] = xs match {
    case Nil => xs
    case y :: ys => if (y > 0) y :: posElems(ys) else posElems(ys)
  }

  override def adults(persons: List[Person]): List[Person] = persons match {
    case Nil     => Nil
    case x :: xs => if (x.age >= 18) x :: adults(xs) else adults(xs)
  }

  override def filter[A](xs: List[A])(f: A => Boolean): List[A] = xs match {
    case Nil                     => Nil
    case head :: tail if f(head) => head :: filter(tail)(f)
    case _    :: tail            => filter(tail)(f)
  }

  override def posElems_viaFilter(xs: List[Int]): List[Int] = filter(xs)(_ > 0)

  override def adults_viaFilter(persons: List[Person]): List[Person] = filter(persons)(person => person.age > 18)

  // FoldRight

  override def sum(ints: List[Int]): Int = ints match {
    case Nil     => 0
    case x :: xs => x + sum(xs)
  }

  override def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = as match {
    case Nil => z
    case x :: xs => f(x, foldRight(xs, z)(f))
  }

  override def sum_viaFoldRight(ints: List[Int]): Int = foldRight(ints, 0)(_ + _)

  override def length_viaFoldRight[A](l: List[A]): Int = foldRight(l, 0)((_, acc) => acc + 1)

  override def concat_viaFoldRight[A](l1: List[A], l2: List[A]): List[A] = foldRight(l1, l2)((x, acc) => x :: acc)

  override def flatten_viaFoldRight[B](xs: List[List[B]]): List[B] = foldRight(xs, List.empty[B])((acc, x) => concat(acc, x))

  override def map_viaFoldRight[A, B](xs: List[A])(f: A => B): List[B] = foldRight(xs, List.empty[B])((x, seq) => f(x) +: seq)

  // FoldLeft

  @tailrec
  override def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B = as match {
    case Nil => z
    case x :: xs => foldLeft(xs, f(z, x))(f)
  }

  override def sum_viaFoldLeft(ints: List[Int]): Int = foldLeft(ints, 0)(_ + _)

  override def length_viaFoldLeft[A](l: List[A]): Int = foldLeft(l, 0)((acc, _) => acc + 1)

  override def reverse_viaFoldLeft[A](l: List[A]): List[A] = foldLeft(l, List.empty[A])((acc, x) => x :: acc)

  // Difícil!!
  override def foldRight_viaFoldLeft[A,B](as: List[A], z: B)(f: (A, B) => B): B = foldLeft(reverse(as), z)((acc, x) => f(x, acc))
}
