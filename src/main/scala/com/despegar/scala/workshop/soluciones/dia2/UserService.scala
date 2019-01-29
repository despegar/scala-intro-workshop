package com.despegar.scala.workshop.soluciones.dia2

import com.despegar.scala.workshop.dia2.{Purchase, User}

import scala.collection.concurrent.TrieMap
import scala.util.{Failure, Success, Try}

object UserCache {

  def getUser(name: String): Option[User] = {
    _cache.get(name)
        .orElse {
          UserRepository.getUser(name)
            .map(user => {
              _cache.update(name, user)
              user
            })
        }
  }

  // NO CAMBIAR
  private val _cache: TrieMap[String, User] = TrieMap(
    "Matt" -> User("2", "Matt", "h4x3r@gmail.com"),
    "Clark" -> User("3", "Clark", "superman@gmail.com")
  )
}

object UserRepository {

  def getUser(name: String): Option[User] = Try(_getUsersViaFakeDB(name))
    .fold(exc => {
        println(s"Error!! ${exc.getMessage}")
        None
    }, {v => Some(v) })

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

  def getPurchases(user: User): Try[List[Purchase]] = Try(_get(user.id)) flatMap {
    case Response(200, purchases) => Success(purchases)
    case Response(errorCode, _) => Failure(new RuntimeException(s"Error retrieving purchases (code: $errorCode)"))
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


/**
  * Es una buena práctica devolver todos los métodos con el mismo Effect. Ej: devolver en todos
  */
object UserService {

  // Obtener un usuario
  def getUser(userName: String): Try[User] = ???

  // Obtener el monto total de todas las compras de un Usuario
  def getPurchaseTotalAmount(userName: String, currency: String): Try[BigDecimal] = {
    getUser(userName)
      .flatMap(user => PurchaseRestClient.getPurchases(user))
      .map(purchases =>
        purchases.foldRight(BigDecimal(0)) {
          case (purchase, total) => total + purchase.amount * AlfredRestClient.getRate(currency)
        })
  }
}
