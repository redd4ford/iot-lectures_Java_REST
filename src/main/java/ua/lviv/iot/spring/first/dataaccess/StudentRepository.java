package ua.lviv.iot.spring.first.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.spring.first.rest.model.Student;
import java.util.List;

// JpaRepository is like CrudRepository, but CrudRepository is an extension. it provides operations
// for saving, returning, searching, updating, and deleting the data for the class Student.

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

  // it is important to write your code according to convention because standard CRUD methods are
  // already pre-written for all your needs and you only have to write the correct method signature.
  // but if you need some super-unique method using query, you can write it as an annotation
  // to Student class

  List<Student> findAllByFirstName(String firstName);

  List<Student> findAllByFirstNameAndLastName(String firstName, String lastName);

   Student findBestStudent();
}
