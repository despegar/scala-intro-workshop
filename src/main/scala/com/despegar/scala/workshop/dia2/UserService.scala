package com.despegar.scala.workshop.dia2

/**
  * Implementar los métodos que faltan, o mejorar los ya implementados.
  *
  * No modificar firma de metodos, salvo el tipo de retorno
  *
  * Agregar tipo de retorno a todos los métodos que no lo tengan, utilizar el que crea mas conveniente
  *
  * Los precios de Purchase están en Dólares
  *
  * ¿Cómo utilizaria los métodos de UserService?
  */

// NO CAMBIAR
case class User(id: String, name: String, email: String)

// NO CAMBIAR
case class Purchase(amount: BigDecimal, description: String)

object UserCache {

  def getUser(name: String) = {
    val user = cached.get(name).orNull
  }

  // NO CAMBIAR
  private val cached = Map(
    "Matt" -> User("2", "Matt", "h4x3r@gmail.com"),
    "Clark" -> User("3", "Clark", "superman@gmail.com")
  )
}

object UserRepository {

  def getUser(name: String) = ???

  def getUsers = ???

  // NO CAMBIAR
  private val users = Map(
    "Richard" -> User("1", "Richard", "richard@gmail.com"),
    "Matt" -> User("2", "Matt", "h4x3r@gmail.com"),
    "Clark" -> User("3", "Clark", "superman@gmail.com"),
    "Peter" -> User("4", "Peter", "parker@gmail.com"),
    "Rob" -> User("5", "Rob", "king@gmail.com")
  ).withDefault(key => throw new RuntimeException(s"User $key doesn't exist"))

}

object PurchaseRestClient {

  def getPurchases(user: User) = ???

  // NO CAMBIAR
  private def _get(userId: String): Response[List[Purchase]] = {
    userId match {
      case "1" => throw new RuntimeException("Socket ERROR")
      case "2" => Response(500, null)
      case "3" => Response(422, null)
      case "4" => Response(200, Purchase(450, "Hotel") :: Purchase(450, "Hotel") :: Purchase(800, "Flight") :: Nil)
      case "5" => Response(200, Purchase(500, "Hotel") :: Nil)
    }
  }

  // NO CAMBIAR
  case class Response[T](statusCode: Int, body: T)

}


object AlfredRestClient {

  def getRate(currency: String) = ???

  // NO CAMBIAR
  private val USDrate = Map(
    "ARS" -> 40,
    "BRL" -> 4,
    "CLP" -> 660
  ).withDefault(key => throw new RuntimeException(s"Currency $key doesn't exist"))

}


object UserService {

  // Obtener un usuario
  def getUser(userName: String) = ???

  // Obtener el monto total de todas las compras de un Usuario
  def getPurchaseTotalAmount(userName: String, currency: String) = ???

}