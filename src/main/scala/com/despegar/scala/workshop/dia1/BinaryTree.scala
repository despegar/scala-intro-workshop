package com.despegar.scala.workshop.dia1

sealed trait BinaryTree[+A]

object BinaryTree {

  def size[A](t: BinaryTree[A]): Int = ???

  def maximum(t: BinaryTree[Int]): Int = ???

  def depth[A](t: BinaryTree[A]): Int = ???

  def map[A,B](t: BinaryTree[A])(f: A => B): BinaryTree[B] = ???

}