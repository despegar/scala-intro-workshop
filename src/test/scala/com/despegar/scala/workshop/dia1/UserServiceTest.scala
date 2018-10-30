package com.despegar.scala.workshop.dia1

import com.despegar.scala.workshop.dia2._
import org.scalatest._

class UserServiceTest extends FlatSpec with Matchers {

  "Cached user " should "be returned" in {
    UserCache.getUser("Matt") should be (User("2", "Matt", "h4x3r@gmail.com"))
  }

  "User in repository" should "be returned" in {
//    UserRepository.getUser("Matt") should be (User("2", "Matt", "h4x3r@gmail.com"))
//    UserRepository.getUser("Clark") should be (User("3", "Clark", "superman@gmail.com"))
  }

  "User count" should "be 5" in {
//    UserRepository.getUsers.size should be (5)
  }

  it should "throw RuntimeException for negative non existent users" in {
    a [RuntimeException] should be thrownBy {
      UserRepository.getUser("Pepe")
    }
  }

  "Purchases" should "be returned for ids 4 and 5" in {
//    PurchaseRestClient.getPurchases(User("4", "" , "")).size should be (3)
//    PurchaseRestClient.getPurchases(User("5", "" , "")).size should be (1)
  }

  "Rate" should "be returned for currency ARS" in {
//    AlfredRestClient.getRate("ARS") should be (40)
  }

  it should "throw RuntimeException for ids 1, 2 or 3" in {
    a [RuntimeException] should be thrownBy {
      PurchaseRestClient.getPurchases(User("1", "", ""))
    }
    a [RuntimeException] should be thrownBy {
      PurchaseRestClient.getPurchases(User("2", "", ""))
    }
    a [RuntimeException] should be thrownBy {
      PurchaseRestClient.getPurchases(User("3", "", ""))
    }
  }

  it should "throw RuntimeException for invalid currency" in {
    a [RuntimeException] should be thrownBy {
      AlfredRestClient.getRate("EUR")
    }
  }

  "User Matt" should "be returned by UserService" in {
//    UserService.getUser("Matt") should be (User("2", "Matt", "h4x3r@gmail.com"))
  }

  "Peter's purchase amount" should "be ARS 68.000" in {
//    UserService.getPurchaseTotalAmount("Peter", "ARS") should be (68000)
  }


}