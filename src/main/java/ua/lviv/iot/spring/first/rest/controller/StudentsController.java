package ua.lviv.iot.spring.first.rest.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.spring.first.business.StudentService;
import ua.lviv.iot.spring.first.rest.model.Student;

// @Controller is an annotation that marks a class as a request and exception handler.

@RequestMapping("/students")
@RestController
public class StudentsController {

  // HashMap is a map based collection class which stores the data in key+value pairs. it stores
  // the data in RAM, therefore you can't get a student you created in a previous session.

  private Map<Integer, Student> students = new HashMap<>();

  // AtomicInteger is used when you can get two or more requests at the same time, and you want to
  // avoid two objects getting the same data (e.g. id). we want to use it instead of ++ because
  // ++ first returns a counter's value and then increments it. if two requests are processed at the
  // same time, it may cause the situation when you lose 1 of 2 objects, and an id point which that
  // object was supposed to take.

  private AtomicInteger idCounter = new AtomicInteger();

  // @Autowired says Spring Boot to create a corresponding studentRepository to this service

  @Autowired
  private StudentService studentService;

  // JSON - JS Object Notation. a human-readable text which consists of object's data in a form of
  // attribute-value pair, e.g.: {"firstName":"pedro","lastName":"aldomovar"}
  // @GetMapping handles HTTP GET requests matched with given URI expression. used to query
  // the server for specific information
  // @PathVariable is used with a handler method parameter to capture the value of a URI template
  // variable.

  @GetMapping(path = "/{id}")
  public Student getStudentById(@PathVariable("id") Integer studentId) {
    return students.get(studentId);
  }

  @GetMapping
  public List<Student> getStudents() {
    return new LinkedList<Student>(students.values());
  }

  // @PostMapping - requests the server to store the message body. usually modeled as an insert
  // or update. at this point we UPDATE an object, even though the method is called createStudent
  // @RequestBody - whatever you can get from the request, put it in a request body and transfer it
  // into object, e.g. Student student. it basically DESERIALIZES JSON string into Java object
  // produces attribute in @PostMapping lets you specify which content type your WebService endpoint
  // can return, e.g. JSON or XML. but this thing is currently not working

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, "application/x-yaml"})
  public Student createStudent(final @RequestBody Student student) {
    System.out.println(studentService.createStudent(student));

    student.setId(idCounter.incrementAndGet());
    students.put(student.getId(), student);
    return student;
  }

  // delete method should not return anything, but we are trying to avoid deleting a non-existing
  // student

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer studentId) {
    HttpStatus status = students.remove(studentId) == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
    return ResponseEntity.status(status).build();
  }

  // PUT returns a PREVIOUS version of the object, therefore you don't really need to return
  // the object

  @PutMapping(path = "/{id}")
  public ResponseEntity<Student> updateStudent(final @PathVariable("id") Integer studentId,
                               final @RequestBody Student student) {
    HttpStatus status = students.put(studentId, student) == null
            ? HttpStatus.NOT_FOUND : HttpStatus.OK;
    return ResponseEntity.status(status).build();
  }

  // this is a shortened version of @GetMapping and @PutMapping but in one line.

  @RequestMapping(method = {RequestMethod.GET, RequestMethod.PUT}, path = "/smartest")
  public Student getTheSmartestStudent() {
    return new Student("quentin", "karantino");
  }

}
