package com.despegar.scala.workshop.dia2

import scala.collection.concurrent.TrieMap
import scala.collection.mutable

/**
  * Implementar los métodos que faltan, o mejorar los ya implementados.
  * No modificar la firma de los metodos, salvo el tipo de retorno
  * Agregar tipo de retorno a todos los métodos que no lo tengan, utilizar el que crea mas conveniente
  * Los precios de Purchase están en Dólares
  * ¿Cómo utilizaría los métodos de UserService?
  */

/**
  * NO CAMBIAR!!
  */
case class User(id: String, name: String, email: String)

/**
  * NO CAMBIAR!!
  */
case class Purchase(amount: BigDecimal, description: String)

object UserCache {

  /**
    * Devuelve un usuario de _cache si existe
    * Si no, va a la base (UserRepository) y después actualiza la cache
    * @param userName Name of user
    * @return ?
    */
  def getUser(userName: String) = ???

  /**
    * NO CAMBIAR!!
    *
    * Para usarlo, buscar como se obtiene una valor de un mapa en Scala
    * Y como se actualiza un valor en un TrieMap
    */
  private val _cache: mutable.Map[String, User] = TrieMap(
    "Matt" -> User("2", "Matt", "h4x3r@gmail.com"),
    "Clark" -> User("3", "Clark", "superman@gmail.com")
  )
}

object UserRepository {

  /**
    * Busca el usuario en la base (user _getUsersViaFakeDB)
    * @param userName Name of user
    * @return ?
    */
  def getUser(userName: String) = ???

  /**
    * Devuelve todos los usuarios (user usersFakeDB)
    * @return ?
    */
  def getUsers = ???

  /**
    * NO CAMBIAR!!
    *
    * Simula ser el método de un conector a la base que devuelve.
    * Si no encuentra el usuario, tira una excepción
    */
  private def _getUsersViaFakeDB(userName: String): User = userName match {
    case "Richard" => User("1", "Richard", "richard@gmail.com")
    case "Matt"    => User("2", "Matt", "h4x3r@gmail.com")
    case "Clark"   => User("3", "Clark", "superman@gmail.com")
    case "Peter"   => User("4", "Peter", "parker@gmail.com")
    case "Rob"     => User("5", "Rob", "king@gmail.com")
    case key       => throw new RuntimeException(s"User $key doesn't exist")
  }

}

object PurchaseRestClient {

  def getPurchases(user: User) = ???

  /**
    * NO CAMBIAR!!
    *
    * Simula ser el método del conector HTTP que hace la request para obtener la
    * lista de purchase de un usuario dado.
    */
  private def _getPurchaseViaFakeHttp(userId: String): Response[List[Purchase]] = {
    userId match {
      case "1" => throw new RuntimeException("Socket ERROR")
      case "2" => Response(500, null)
      case "3" => Response(422, null)
      case "4" => Response(200, Purchase(450, "Hotel") :: Purchase(450, "Hotel") :: Purchase(800, "Flight") :: Nil)
      case "5" => Response(200, Purchase(500, "Hotel") :: Nil)
    }
  }

  /**
    * NO CAMBIAR!!
    *
    * @param statusCode response status code
    * @param body response body
    * @tparam T type of response body
    */
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

  /**
    * Devuelve un usuario
    * Obtiene el usuario mediante la cache
    * @param userName Name of user
    * @return
    */
  def getUser(userName: String) = ???

  /**
    * Devuelve el monto total de todas las compras de un usuario
    * @param userName Name of user
    * @param currency currency of total amount
    * @return
    */
  def getPurchaseTotalAmount(userName: String, currency: String) = ???

}