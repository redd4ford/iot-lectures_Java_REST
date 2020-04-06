package ua.lviv.iot.spring.first.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.lviv.iot.spring.first.dataaccess.StudentRepository;
import ua.lviv.iot.spring.first.rest.model.Student;
import java.util.List;

// we use Services to increase the code's readability, but basically all it does is operates
// the repository of students.

@Service
public class StudentService {
  @Autowired
  private StudentRepository studentRepository;

  public List<Student> getStudents() {
    return studentRepository.findAll();
  }

  public ResponseEntity<Student> getStudentById(Integer studentId) {
    if (studentRepository.findById(studentId).isPresent()) {
      return new ResponseEntity<>(studentRepository.getOne(studentId), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  public Student createStudent(Student student) {
    return studentRepository.save(student);
  }

  public ResponseEntity<Student> updateStudent(Integer studentId, Student updatedStudent) {
    if (studentRepository.findById(studentId).isPresent()) {
      Student student = studentRepository.getOne(studentId);
      student = updatedStudent;
      studentRepository.save(student);

      return new ResponseEntity<>(student, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  public ResponseEntity<Student> deleteStudent(Integer studentId) {
    if (studentRepository.findById(studentId).isPresent()) {
      studentRepository.deleteById(studentId);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
