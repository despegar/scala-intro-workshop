package com.despegar.scala.workshop.soluciones.dia1

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

  def students(ids: Set[Int]): Seq[Student] = _students.filter(student => ids.contains(student.id))

  def student(id: Int): Option[Student] = _students.find(student => id == student.id)

  def courses(ids: Seq[Int]): Seq[Course] = _courses.filter(course => ids.contains(course.id))

  def course(id: Int): Option[Course] = _courses.find(course => id == course.id)

  /**
    * A nerd has to get a grade greater or equals than `MinNerdGrade`
    */
  def isNerd(grade: BigDecimal): Boolean = grade >= MinNerdGrade

  def courseStudentNerds: Seq[CourseStudent] = _courseStudent.filter(cs => isNerd(cs.grade)).distinct

  def nerdIds: Set[Int] = courseStudentNerds.map(cs => cs.studentId).toSet

  def nerds: Seq[Student] = students(nerdIds)

  /**
    * A partir de ahora, va a subir un poco la complejidad...
    */

  /**
    * first nerd found
    */
  def firstNerd: Option[Student] = _courseStudent
    .find(cs => isNerd(cs.grade))
    .flatMap(cs => student(cs.studentId))


  def firstNerdBetter: Option[Student] = nerds.headOption

  def secondNerd: Option[Student] = nerdIds.drop(1).headOption.flatMap(id => student(id))

  def sortedNerds: Seq[Student] = courseStudentNerds
    .sortWith(_.grade > _.grade)
    .flatMap(cs => student(cs.studentId))
    .distinct

  /**
    * nerd in position N, sorted by grade
    */
  def topNerdAtPosition(n: Int): Option[Student] = sortedNerds.drop(n - 1).headOption

  /**
    * nerd position of a given student ID
    */
  def nerdPosition(id: Int): Option[Int] = sortedNerds
    .toStream
    .zipWithIndex
    .find(_._1.id == id)
    .map(_._2 + 1)

  /**
    * first N nerds, sorted by grade
    */
  def firstNerds(n: Int): Seq[Student] = sortedNerds.take(n)

  def firstNerdOlderThan(minAge: Int): Option[Student] = nerds.find(_.age >= minAge)

  /**
    * traverse the list only one time
    */
  def firstNerdOlderThan_withOneTraverse(minAge: Int): Option[Student] =
    _courseStudent
      .toStream
      .filter(cs => {
        println(s"Iterating $cs")
        cs.grade >= MinNerdGrade
      })
      .flatMap(cs => student(cs.studentId))
      .find(_.age >= minAge)

  def mostNerdy: Option[Student] = _courseStudent
    .filter(cs => isNerd(cs.grade))
    .sortWith(_.grade > _.grade)
    .headOption
    .flatMap(courseStudent => student(courseStudent.studentId))

  /**
    * Los siguientes son mucho mas dificil. Dont worry! No pretendemos que lo puedas hacer.
    */

  def courseStudentsMapped: Map[CourseStudent, (Course, Student)] = _courseStudent
    .flatMap(courseStudent =>
      course(courseStudent.courseId)
        .flatMap(course => student(courseStudent.studentId)
          .map(student => courseStudent -> ((course, student)))))
    .toMap

  def courseStudentsMapped_viaFor: Map[CourseStudent, (Course, Student)] = (for {
    courseStudent      <- _courseStudent
    course             <- course(courseStudent.courseId)
    student            <- student(courseStudent.studentId)
  } yield courseStudent -> ((course, student))).toMap

  def studentsByCourse: Map[Course, Seq[Student]] =
    for {
      (courseId, courseStudents) <- _courseStudent.groupBy(_.courseId)
      c                          <- course(courseId)
    } yield c -> students(courseStudents.map(_.studentId).toSet)

  def nerdInAllCourses: Seq[Student] = _courseStudent
    .groupBy(_.studentId)
    .collect { case (studentId, courseStudents) if courseStudents.forall(cs => isNerd(cs.grade)) =>
      student(studentId)
    }
    .flatten
    .toSeq

  println(s"All nerds: $nerds")

}

case class Student(id: Int, name: String, age: Int)
case class Course(id: Int, name: String)
case class CourseStudent(courseId: Int, studentId: Int, grade: BigDecimal)


