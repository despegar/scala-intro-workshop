package com.despegar.scala.workshop.examples

import com.despegar.scala.workshop.soluciones.dia1.{Student, StudentsAnalyzer}


object EffectsExamples {

  object StudentService {

    def studentDescription(id: Int): Either[String, Student] = {
      StudentsAnalyzer
        .student(id = 1)                     // Option[Student]
        .filter(_.age > 20)                  // Option[Student]
        .map(desc => Right(desc))            // Option[Either[Any, Student]]
        .getOrElse(Left(s"User not found"))  // Either[String, Student]
    }
  }

  object StudentController {

    private val studentService = StudentService

    def getDescription(id: Int): Response = {
      studentService
        .studentDescription(id)
        .map(studentToDescription)
        .map(description => Response(status = 200, body = description))
        .left.map(error => Response(status = 400, body = error))
        .merge
    }

    private def studentToDescription(s: Student): String =
      s"${s.name}, ${s.age} years old."
  }
}

case class Response(status: Int, body: String)
