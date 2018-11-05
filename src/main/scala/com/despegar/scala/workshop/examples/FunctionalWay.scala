package com.despegar.scala.workshop.examples

object FunctionalWay {

  def printArgs(args: Array[String]): Unit = {
    var i = 0
    while (i < args.length) {
      println(args(i))
      i += 1
    }
  }

  def printArgsV2(args: Array[String]): Unit =
    for (arg <- args)
      println(arg)

  def printArgsV3(args: Array[String]): Unit =
    args.foreach(println)

  def formatArgs(args: Array[String]): String =
    args.mkString("\n")

  def printArgsV4(args: Array[String]): Unit =
    println(formatArgs(args))

}
