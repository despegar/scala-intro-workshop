package com.despegar.scala.workshop.soluciones.dia1

sealed trait BinaryTree[+A]
case class Leaf[A](value: A) extends BinaryTree[A]
case class Branch[A](left: BinaryTree[A], right: BinaryTree[A]) extends BinaryTree[A]

object BinaryTree {

  def size[A](t: BinaryTree[A]): Int = t match {
    case Leaf(_) => 1
    case Branch(l,r) => 1 + size(l) + size(r)
  }

  def maximum(t: BinaryTree[Int]): Int = t match {
    case Leaf(n) => n
    case Branch(l,r) => maximum(l) max maximum(r)
  }

  def depth[A](t: BinaryTree[A]): Int = t match {
    case Leaf(_) => 0
    case Branch(l,r) => 1 + (depth(l) max depth(r))
  }

  def map[A,B](t: BinaryTree[A])(f: A => B): BinaryTree[B] = t match {
    case Leaf(a) => Leaf(f(a))
    case Branch(l,r) => Branch(map(l)(f), map(r)(f))
  }

}