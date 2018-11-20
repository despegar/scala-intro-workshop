package com.despegar.scala.workshop.dia1

/**
  * Implementar todos los ???
  */

object StudentsAnalyzer extends App {

  val MinNerdGrade: BigDecimal = 9

  val _students: List[Student] = List(
    Student(id = 1, "Bob", age = 21),
    Student(id = 2, "Alice", age = 20),
    Student(id = 3, "Tom", age = 23),
    Student(id = 4, "Sam", age = 22),
    Student(id = 5, "Mark", age = 19),
    Student(id = 6, "Brian", age = 21),
    Student(id = 7, "Loren", age = 21),
    Student(id = 8, "John", age = 21),
    Student(id = 9, "Carl", age = 21))

  val _courses: List[Course] = List(
    Course(id = 1, "Java"),
    Course(id = 2, "Scala"),
    Course(id = 3, "Kotlin"),
    Course(id = 4, "C++"),
    Course(id = 5, "Rust"),
    Course(id = 6, "Go"),
    Course(id = 7, "Python"),
    Course(id = 8, "Haskell"))

  val _courseStudent: List[CourseStudent] = List(
    CourseStudent(courseId = 1, studentId = 1, grade = 7.5),
    CourseStudent(courseId = 1, studentId = 2, grade = 8.6),
    CourseStudent(courseId = 1, studentId = 3, grade = 9.5),
    CourseStudent(courseId = 2, studentId = 1, grade = 8.0),
    CourseStudent(courseId = 2, studentId = 2, grade = 9.9),
    CourseStudent(courseId = 2, studentId = 3, grade = 9.1),
    CourseStudent(courseId = 2, studentId = 4, grade = 7.8),
    CourseStudent(courseId = 2, studentId = 5, grade = 4.0),
    CourseStudent(courseId = 3, studentId = 2, grade = 9.9),
    CourseStudent(courseId = 3, studentId = 8, grade = 6.2),
    CourseStudent(courseId = 4, studentId = 1, grade = 8.5),
    CourseStudent(courseId = 5, studentId = 1, grade = 7.6),
    CourseStudent(courseId = 5, studentId = 2, grade = 7.5),
    CourseStudent(courseId = 7, studentId = 1, grade = 5.5),
    CourseStudent(courseId = 8, studentId = 1, grade = 7.8)
  )

  /**
    * Some hints:
    *
    * 1) List.flatMap(f) can receive an f that return an Option. e.g.:
    *
    * def safeDivide(a: Int, b: Int): Option[Int] = if (b != 0) Some(a / b) else None
    * List(0,1,2).flatMap(i => safeDivide(10, i)) == List(10, 5)
    *
    * 2) Useful methods:
    *
    * - filter
    * - find
    * - distinct
    * - toSet
    * - toSeq
    * - map
    * - flatMap
    * - collect
    * - headOption
    * - drop(n)
    * - take(n)
    * - sortWith
    * - toStream
    */

  def students(ids: Seq[Int]): Seq[Student] = _students.filter(student => ids.contains(student.id))

  def student(id: Int): Option[Student] = _students.find(student => id == student.id)

  def courses(ids: Seq[Int]): Seq[Course] = ???

  def course(id: Int): Option[Course] = ???

  /**
    * A nerd has to get a grade greater or equals than `MinNerdGrade`
    */
  def isNerd(grade: BigDecimal): Boolean = ???

  /**
    * It must not have repeat students
    */
  def courseStudentNerds: Seq[CourseStudent] = ???

  def nerdIds: Set[Int] = ???

  def nerds: Seq[Student] = ???

  /**
    * A partir de ahora, va a subir un poco la complejidad...
    */

  /**
    * first nerd found
    */
  def firstNerd: Option[Student] = ???

  def secondNerd: Option[Student] = ???

  /**
    * It must not have repeat students
    */
  def sortedNerds: Seq[Student] = ???

  /**
    * nerd in position N, sorted by grade
    */
  def topNerdAtPosition(n: Int): Option[Student] = ???

  /**
    * nerd position of a given student ID
    */
  def nerdPosition(id: Int): Option[Int] = ???

  /**
    * first N nerds, sorted by grade
    */
  def firstNerds(n: Int): Seq[Student] = ???

  def firstNerdOlderThan(minAge: Int): Option[Student] = ???

  /**
    * traverse the list only one time
    */
  def firstNerdOlderThan_withOneTraverse(minAge: Int): Option[Student] = ???

  def mostNerdy: Option[Student] = ???

  /**
    * Los siguientes son mucho mas dificil.
    * Dont worry!
    * No pretendemos que lo puedas hacer, con tan poca practica.
    * Pero vale la pena intentarlo ;)
    */

  def courseStudentsMapped: Map[CourseStudent, (Course, Student)] = ???

  def courseStudentsMapped_viaFor: Map[CourseStudent, (Course, Student)] = ???

  def studentsByCourse: Map[Course, Seq[Student]] = ???

  def nerdInAllCourses: Seq[Student] =  ???

//  println(s"All nerds: $nerds")

}

case class Student(id: Int, name: String, age: Int)
case class Course(id: Int, name: String)
case class CourseStudent(courseId: Int, studentId: Int, grade: BigDecimal)


