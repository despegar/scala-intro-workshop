package com.despegar.scala.workshop.dia1

import org.scalatest._

class ListUtilsTest extends FlatSpec with Matchers {

  val listUtils: ListUtils = ListUtils

  "Length of a list" should "number of elements that it contains" in {
    listUtils.length(List.empty[Int]) should be (0)
    listUtils.length(List(1,2)) should be (2)
  }

  "Last element of a list" should "be found" in {
    a [RuntimeException] should be thrownBy listUtils.last(List.empty[Int])
    listUtils.last(List(1)) should be (1)
    listUtils.last(List(1,2)) should be (2)
  }

  "List.init" should "return all elements except the last one" in {
    a [RuntimeException] should be thrownBy listUtils.last(List.empty[Int])
    listUtils.last(List(1)) should be (1)
    listUtils.last(List(1,2)) should be (2)
  }

  "Collection emptiness " should "be properly determined" in {
    listUtils.isEmpty(Nil) should be (true)
    listUtils.isEmpty(List(1)) should be (false)
    listUtils.isEmpty(List(1,2)) should be (false)
  }

  "dropWhile over List" should "be a list truncate until condition is not longer true" in {
    listUtils.dropWhile(List.empty[Int], (x: Int) => x > 5) should be (Nil)
    listUtils.dropWhile(List(1), (x: Int) => x < 5) should be (Nil)
    listUtils.dropWhile(List(1), (x: Int) => x > 5) should be (List(1))
    listUtils.dropWhile(List(1, 2, 3), (x: Int) => x < 2) should be (List(2, 3))
  }

  "concat over list 1 and list 2" should "be the elements of list1 plus the elements of list 2" in {
    listUtils.concat(List.empty[Int], Nil) should be (Nil)
    listUtils.concat(List(1), Nil) should be (List(1))
    listUtils.concat(List(1, 2, 3), Nil) should be (List(1, 2, 3))
    listUtils.concat(List.empty[Int], List(1, 2, 3)) should be (List(1, 2, 3))
    listUtils.concat(List(1, 2, 3), List(4, 5, 6)) should be (List(1, 2, 3, 4, 5, 6))
  }

  "flatten" should "all sub list appended" in {
    listUtils.flatten(List.empty[List[Int]]) should be (Nil)
    listUtils.flatten(List(List.empty[Int])) should be (Nil)
    listUtils.flatten(List(List(1))) should be (List(1))
    listUtils.flatten(List(List(1), List(2, 3))) should be (List(1, 2, 3))
    listUtils.flatten(List(List(1, 2), Nil)) should be (List(1, 2))
  }

  "reverse of list" should "be all the element in opposite order" in {
    listUtils.reverse(List.empty[List[Int]]) should be (Nil)
    listUtils.reverse(List(1, 2, 3)) should be (List(3, 2, 1))
    listUtils.reverse(List(1)) should be (List(1))
  }

  "scaleList" should "be correctly implemented using recursion" in {
    listUtils.scaleList(Nil, 3) should be (Nil)
    listUtils.scaleList(List(1, 2, 3), 3) should be (List(3, 6, 9))
  }

  "ages" should "return the age of each person" in {
    listUtils.ages(Nil) should be (Nil)
    listUtils.ages(List(Person(age = 10), Person(age = 21))) should be (List(10, 21))
  }

  "A list map by f" should "be a new list with the result of f apply to each element" in {
    listUtils.map(List.empty[Int])((x: Int) => x + 1) should be (Nil)
    listUtils.map(List(1, 2, 3))(_ + 1) should be (List(2, 3, 4))
  }

  "A list flatMap by f" should "be a new list with the result of f apply to each element appended" in {
    listUtils.flatMap(List.empty[Int])((x: Int) => List(x + 1, x + 2)) should be (Nil)
    listUtils.flatMap(List(1, 2, 3))((x: Int) => List(x + 1, x + 2)) should be (List(2, 3, 3, 4, 4, 5))
  }

  "scaleList_viaMap" should "be correctly implemented using map" in {
    listUtils.scaleList_viaMap(Nil, 3) should be (Nil)
    listUtils.scaleList_viaMap(List(1, 2, 3), 3) should be (List(3, 6, 9))
  }

  "ages_viaMap" should "be correctly implemented using map" in {
    listUtils.ages_viaMap(Nil) should be (Nil)
    listUtils.ages_viaMap(List(Person(age = 10), Person(age = 21))) should be (List(10, 21))
  }

  "posElems" should "return all positive elements" in {
    listUtils.posElems(Nil) should be (Nil)
    listUtils.posElems(List(-1, 0, 1)) should be (List(1))
  }

  "adults" should "return all person with age >= 18" in {
    listUtils.adults(Nil) should be (Nil)
    listUtils.adults(List(Person(age = 10), Person(age = 21))) should be (List(Person(age = 21)))
  }

  "filter function" should "return the elementos of the list that satisfy the condition" in {
    listUtils.filter(Nil)(identity) should be (Nil)
    listUtils.filter(List(1,2,3,4,5))(_ > 2) should be (List(3,4,5))
  }

  "posElems_viaFilter function" should "be correctly implemented using filter" in {
    listUtils.posElems_viaFilter(Nil) should be (Nil)
    listUtils.posElems_viaFilter(List(-1, 0, 1)) should be (List(1))
  }

  "adults_viaFilter function" should "be correctly implemented using filter" in {
    listUtils.adults_viaFilter(Nil) should be (Nil)
    listUtils.adults_viaFilter(List(Person(age = 10), Person(age = 21))) should be (List(Person(age = 21)))
  }

  "sum function" should "return sum of all elements" in {
    listUtils.sum(Nil) should be (0)
    listUtils.sum(List(1, 2, 3, 4)) should be (10)
  }

  "foldRight" should "return the elements operated by the given function and the given init value" in {
    listUtils.foldRight(Nil, 0)((_, x) => x) should be (0)
    listUtils.foldRight(List("a", "b", "c"), "-")((string, expression) => expression + string) should be ("-cba")
  }

  "sum_viaFoldRight function" should "be correctly implemented using foldRight" in {
    listUtils.sum_viaFoldRight(Nil) should be (0)
    listUtils.sum_viaFoldRight(List(1, 2, 3, 4)) should be (10)
  }

  "Length of a list" should "be correctly implemented using foldRight" in {
    listUtils.length_viaFoldRight(List.empty[Int]) should be (0)
    listUtils.length_viaFoldRight(List(1,2)) should be (2)
  }

  "concat_viaFoldRight" should "be correctly implemented using foldRight" in {
    listUtils.concat_viaFoldRight(List.empty[Int], Nil) should be (Nil)
    listUtils.concat_viaFoldRight(List(1), Nil) should be (List(1))
    listUtils.concat_viaFoldRight(List(1, 2, 3), Nil) should be (List(1, 2, 3))
    listUtils.concat_viaFoldRight(List.empty[Int], List(1, 2, 3)) should be (List(1, 2, 3))
    listUtils.concat_viaFoldRight(List(1, 2, 3), List(4, 5, 6)) should be (List(1, 2, 3, 4, 5, 6))
  }

  "flatten_viaFoldRight" should "be correctly implemented using foldRight" in {
    listUtils.flatten_viaFoldRight(List.empty[List[Int]]) should be (Nil)
    listUtils.flatten_viaFoldRight(List(List.empty[Int])) should be (Nil)
    listUtils.flatten_viaFoldRight(List(List(1))) should be (List(1))
    listUtils.flatten_viaFoldRight(List(List(1), List(2, 3))) should be (List(1, 2, 3))
    listUtils.flatten_viaFoldRight(List(List(1, 2), Nil)) should be (List(1, 2))
  }

  "map_viaFoldRight" should "be correctly implemented using foldRight" in {
    listUtils.map_viaFoldRight(List.empty[Int])((x: Int) => x + 1) should be (Nil)
    listUtils.map_viaFoldRight(List(1, 2, 3))(_ + 1) should be (List(2, 3, 4))
  }

  "foldLeft" should "return the elements operated by the given function and the given init value" in {
    listUtils.foldLeft(Nil, 0)((_, x) => x) should be (0)
    listUtils.foldLeft(List("a", "b", "c"), "-")((expression, string) => expression + string) should be ("-abc")
  }

  "sum_viaFoldLeft function" should "be correctly implemented using foldLeft" in {
    listUtils.sum_viaFoldLeft(Nil) should be (0)
    listUtils.sum_viaFoldLeft(List(1, 2, 3, 4)) should be (10)
  }

  "Length of a list" should "be correctly implemented using foldLeft" in {
    listUtils.length_viaFoldLeft(List.empty[Int]) should be (0)
    listUtils.length_viaFoldLeft(List(1,2)) should be (2)
  }

  "reverse of list" should "be correctly implemented using foldLeft" in {
    listUtils.reverse_viaFoldLeft(List.empty[List[Int]]) should be (Nil)
    listUtils.reverse_viaFoldLeft(List(1, 2, 3)) should be (List(3, 2, 1))
    listUtils.reverse_viaFoldLeft(List(1)) should be (List(1))
  }

  "foldRight" should "be correctly implemented using foldLeft" in {
    listUtils.foldRight_viaFoldLeft(Nil, 0)((_, x) => x) should be (0)
    listUtils.foldRight_viaFoldLeft(List("a", "b", "c"), "-")((string, expression) => expression + string) should be ("-cba")
  }
}