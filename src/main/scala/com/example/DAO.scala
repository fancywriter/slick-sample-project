package com.example

import java.util.UUID

import slick.driver.JdbcProfile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class DAO(val driver: JdbcProfile) {

  import driver.api._

  private class People(tag: Tag) extends Table[Person](tag, "PEOPLE") {
    def id = column[Option[UUID]]("ID", O.PrimaryKey)

    def firstName = column[String]("FIRST_NAME")

    def lastName = column[String]("LAST_NAME")

    override def * = (id, firstName, lastName) <>(Person.tupled, Person.unapply)
  }

  private val query = TableQuery[People]

  def createSchema(implicit db: Database) = db.run(query.schema.create)

  def insert(person: Person)(implicit db: Database): Future[Person] = {
    val p = person.copy(id = Some(UUID.randomUUID()))
    db.run(query += p).map(_ => p)
  }

  def findById(id: UUID)(implicit db: Database) = db.run(query.filter(_.id === id).result.headOption)

  def deleteById(id: UUID)(implicit db: Database) = db.run(query.filter(_.id === id).delete)

  def updateById(id: UUID, person: Person)(implicit db: Database) = db.run(query.filter(_.id === id).update(person.copy(id = Some(id))))

}
