package com.despegar.scala.workshop.dia1

import org.scalatest._

class IntListUtilsTest extends FlatSpec with Matchers {

  "Collection emptiness " should "be properly determined" in {
    IntListUtils.isEmpty(Nil) should be (true)
    IntListUtils.isEmpty(Seq(1)) should be (false)
    IntListUtils.isEmpty(Seq(1,2)) should be (false)
  }

  "Element " should "be be found in collection" in {
    IntListUtils.find(Nil, _ == 1) should be (None)
    IntListUtils.find(Seq(1), _ == 1) should be (Some(1))
    IntListUtils.find(Seq(2,1), _ == 1) should be (Some(1))
    IntListUtils.find(Seq(2), _ == 1) should be (None)
  }

  "Function " should "be executed as many times as elements in collection" in {
    var res = Seq.empty[String]
    IntListUtils.foreach(Nil, n => res = res :+ s"$n")
    res should be (Nil)

    res = Seq.empty[String]
    IntListUtils.foreach(Seq(1), n => res = res :+ s"$n")
    res should be (Seq("1"))

    res = Seq.empty[String]
    IntListUtils.foreach(Seq(2,1), n => res = res :+ s"$n")
    res should be (Seq("2","1"))
  }

  "Element " should "be mapped" in {
    IntListUtils.map(Nil, _ + 1) should be (Nil)
    IntListUtils.map(Seq(1), _ + 1) should be (Seq(2))
    IntListUtils.map(Seq(2,1), _ + 1) should be (Seq(3,2))
  }

  "List " should "be filtered" in {
    IntListUtils.filter(Nil, _ > 1) should be (Nil)
    IntListUtils.filter(Seq(1), _ > 1) should be (Nil)
    IntListUtils.filter(Seq(2,1), _ > 1) should be (Seq(2))
  }

  "Take 3 elements " should " return the first 3 items in collection" in {
    IntListUtils.take(Nil, 3) should be (Nil)
    IntListUtils.take(Seq(1,3,5), 3) should be (Seq(1,3,5))
    IntListUtils.take(Seq(2,1,0,5,4), 3) should be (Seq(2,1,0))
  }

  "Drop 3 elements " should " remove the first 3 items in collection" in {
    IntListUtils.take(Nil, 3) should be (Nil)
    IntListUtils.take(Seq(1,3,5), 3) should be (Nil)
    IntListUtils.take(Seq(2,1,0,5,4), 3) should be (Seq(5,4))
  }

}