package com.despegar.scala.workshop.examples

object CaseClassImplementation {

}

case class User(id: Int, name: String)

class ManuallyUser(val id: Int, val name: String) extends Product with Serializable {
  // El compilador aca escribe muchos métodos hardcodeados, como
  // copy(), toString, hashCode(), equals()
  override def productElement(n: Int): Any = ???
  override def productArity: Int = ???
  override def canEqual(that: Any): Boolean = ???
  override def toString: String = s"ManuallyUSer($id,$name)"
}

object ManuallyUser {

  def apply(id: Int, name: String): ManuallyUser = new ManuallyUser(id, name)

  // for pattern matching
  def unapply(manuallyUser: ManuallyUser): Option[(Int, String)] = Some((manuallyUser.id, manuallyUser.name))
}

object Examples {

  val user1 = ManuallyUser(1, "pepe")
  val user2 = ManuallyUser(2, "pepe")

  user1 match {
    case ManuallyUser(1, "pepe") => "Yes!"
    case x => s"No: $x"
  }

  user2 match {
    case ManuallyUser(1, "pepe") => "Yes!"
    case x => s"No: $x"
  }
}
