package com.despegar.scala.workshop.soluciones.dia2

import com.despegar.scala.workshop.dia2.{Purchase, User}

import scala.collection.concurrent.TrieMap
import scala.collection.mutable
import scala.util.{Failure, Success, Try}

object UserCache {

  def getUser(name: String): User = _cache.getOrElseUpdate(name, UserRepository.getUser(name))

  // NO CAMBIAR
  private val _cache: mutable.Map[String, User] = TrieMap(
    "Matt" -> User("2", "Matt", "h4x3r@gmail.com"),
    "Clark" -> User("3", "Clark", "superman@gmail.com")
  )
}

object UserRepository {

  def getUser(name: String): User = users(name)

  def getUsers: Set[User] = users.values.toSet

  // NO CAMBIAR
  private val users = Map(
    "Richard" -> User("1", "Richard", "richard@gmail.com"),
    "Matt"    -> User("2", "Matt", "h4x3r@gmail.com"),
    "Clark"   -> User("3", "Clark", "superman@gmail.com"),
    "Peter"   -> User("4", "Peter", "parker@gmail.com"),
    "Rob"     -> User("5", "Rob", "king@gmail.com")
  ).withDefault(key => throw new RuntimeException(s"User $key doesn't exist"))
}

object PurchaseRestClient {

  def getPurchases(user: User): List[Purchase] = Try(_get(user.id)) match {
    case Success(Response(200, purchases)) => purchases
    case Success(Response(errorCode, _)) => throw new RuntimeException(s"Error retrieving purchases (code: $errorCode)")
    case Failure(e) => throw new RuntimeException("Error retrieving purchases", e)
  }

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

  def getRate(currency: String): BigDecimal = USDrate(currency)

  // NO CAMBIAR
  private val USDrate = Map(
    "ARS" -> 40,
    "BRL" -> 4,
    "CLP" -> 660
  ).withDefault(key => throw new RuntimeException(s"Currency $key doesn't exist"))

}

object UserService {

  // Obtener un usuario
  def getUser(userName: String): Try[User] = Try(UserCache.getUser(userName))

  // Obtener el monto total de todas las compras de un Usuario
  def getPurchaseTotalAmount(userName: String, currency: String): Try[BigDecimal] = {
    getUser(userName)
      .flatMap(user => Try {
        PurchaseRestClient.getPurchases(user).foldRight(BigDecimal(0)) {
          case (purchase, total) => total + purchase.amount * AlfredRestClient.getRate(currency)
        }
      })
  }
}
