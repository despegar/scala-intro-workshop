package com.despegar.scala.workshop.dia2

object ADTViaCaseClass {

  // First attempt
  object ImperativeApproach {

    abstract class Expr {
      def isNumber: Boolean
      def isSum: Boolean
      def numValue: Int
      def leftOp: Expr
      def rightOp: Expr
    }
    class Number(n: Int) extends Expr {
      def isNumber: Boolean = true
      def isSum: Boolean = false
      def numValue: Int = n
      def leftOp: Expr = error("Number.leftOp")
      def rightOp: Expr = error("Number.rightOp")
    }
    class Sum(e1: Expr, e2: Expr) extends Expr {
      def isNumber: Boolean = false
      def isSum: Boolean = true
      def numValue: Int = error("Sum.numValue")
      def leftOp: Expr = e1
      def rightOp: Expr = e2
    }

    // Operation 'evaluation'
    def eval(e: Expr): Int = {
      if (e.isNumber) e.numValue
      else if (e.isSum) eval(e.leftOp) + eval(e.rightOp)
      else error("unrecognized expression kind")
    }

    // 10 + 2 + 4 = 16
    eval(new Sum(
      new Number(10),
      new Sum(
        new Number(2),
        new Number(4)))) // 16

    // How to add Prod expression?
    // It's necessary to write a new class and add a new method in each other classes :(

    // Add a new operation: 'pretty print'
    def prettyPrint(e: Expr): String= {
      if (e.isNumber)   e.numValue.toString
      else if (e.isSum) s"(${prettyPrint(e.leftOp)}+${prettyPrint(e.rightOp)})"
      else error("unrecognized expression kind")
    }
    def test = {
      prettyPrint(new Sum(new Number(3), new Number(4))) // "3 + 4"
    }
  }

  // Second attempt
  object OOApproach {
    abstract class Expr {
      def eval: Int
      def prettyPrint: String
    }
    class Number(n: Int) extends Expr {
      def eval: Int = n
      def prettyPrint: String = n.toString
    }
    class Sum(e1: Expr, e2: Expr) extends Expr {
      def eval: Int = e1.eval + e2.eval
      def prettyPrint: String = s"(${e1.prettyPrint}+${e2.prettyPrint})"
    }

    // Add Prod expression:
    class Prod(e1: Expr, e2: Expr) extends Expr {
      def eval: Int = e1.eval * e2.eval
      def prettyPrint: String = s"(${e1.prettyPrint}*${e2.prettyPrint})"
    }

    // How to add the operation 'pretty print'?
    // It's necessary to modify each other expression adding a new method :(
  }

  // Third attempt
  object FunctionalApproach {

    // Algebraic Data Type:
    sealed trait Expr
    case class Number(n: Int) extends Expr
    case class Sum(e1: Expr, e2: Expr) extends Expr
    case class Prod(e1: Expr, e2: Expr) extends Expr

    // Operations:
    def eval(e: Expr): Int = e match {
      case Number(n) => n
      case Sum(l, r) => eval(l) + eval(r)
      case Prod(l, r) => eval(l) * eval(r)
    }

    def prettyPrint(e: Expr): String = e match {
      case Number(n) => n.toString
      case Sum(l, r) => s"(${prettyPrint(l)}+${prettyPrint(r)})"
      case Prod(l, r) => s"(${prettyPrint(l)}*${prettyPrint(r)})"
    }
  }

  def error(msg: String): Nothing = throw new RuntimeException(msg)
}
