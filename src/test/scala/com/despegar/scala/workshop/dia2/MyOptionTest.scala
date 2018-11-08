package com.despegar.scala.workshop.dia2

import org.scalatest.{FlatSpec, Matchers}

class MyOptionTest extends FlatSpec with Matchers {

  "A nullable value that is not null" should "be wrapper by MySome" in {
//    val nullableValue = "a value"
//    MyOption(nullableValue) should be (MySome(nullableValue))
  }

  "A nullable value that is null" should "be wrapper by MyNone" in {
//    val nullableValue = null
//    MyOption(nullableValue) should be (MyNone)
  }

  "MySome#isEmpty" should "be false" in {
//    MySome("a value").isEmpty should be (false)
  }

  "MyNone#isEmpty" should "be true" in {
//    MyNone.isEmpty should be (true)
  }

  "MySome#nonEmpty" should "be true" in {
//    MySome("a value").nonEmpty should be (true)
  }

  "MyNone#nonEmpty" should "be false" in {
//    MyNone.nonEmpty should be (false)
  }

  "MySome#getOrElse" should "be true" in {
//    MySome("a value").getOrElse("other value") should be ("a value")
  }

  "MyNone#getOrElse" should "be false" in {
//    MyNone.getOrElse("other value") should be ("other value")
  }

  "MySome#orElse" should "be true" in {
//    MySome("a value").orElse(MySome("other value")) should be (MySome("a value"))
  }

  "MyNone#orElse" should "be false" in {
//    MyNone.orElse(MySome("other value")) should be (MySome("other value"))
  }

  "MySome#isDefined" should "be true" in {
//    MySome("a value").isDefined should be (true)
  }

  "MyNone#isDefined" should "be false" in {
//    MyNone.isDefined should be (false)
  }

  "MySome map" should "apply a function and keep the same structure MySome" in {
//    MySome("a value").map(str => str + "...") should be (MySome("a value..."))
  }

  "MyNone map" should "not apply a the function and keep the same structure MyNone" in {
//    MyOption.empty[String].map(str => str + "...") should be (MyNone)
  }

  "MySome flatMap" should "apply a function and return the function result" in {
//    MySome("a value").flatMap(str => MySome(str + "...")) should be (MySome("a value..."))
  }

  "MyNone flatMap" should "apply a function and return the function result" in {
//    MyOption.empty[String].flatMap(str => MySome(str + "...")) should be (MyNone)
  }

}
