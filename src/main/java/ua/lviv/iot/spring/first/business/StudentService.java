package ua.lviv.iot.spring.first.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.spring.first.dataaccess.StudentRepository;
import ua.lviv.iot.spring.first.rest.model.Student;

@Service
public class StudentService {
  @Autowired
  private StudentRepository studentRepository;

  // you may rename it to persist because create and update have similar syntax

  public Student createStudent(Student student) {
    return studentRepository.save(student);
  }

}
