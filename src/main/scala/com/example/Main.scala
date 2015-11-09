package com.example

import slick.driver.H2Driver
import slick.jdbc.JdbcBackend.Database

import scala.concurrent.Await
import scala.util.{Success, Failure}
import concurrent.duration._

import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {

  val dao = new DAO(H2Driver)
  implicit val db = Database.forURL("jdbc:h2:mem:example123;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")

  val f1 = dao.createSchema
  val f2 = f1.flatMap(_ => dao.insert(Person(None, "john", "smith")))
  val f3 = f2.flatMap(p => dao.findById(p.id.get))

  println(Await.result(f2, 10.seconds))
  println(Await.result(f3, 10.seconds))
}
