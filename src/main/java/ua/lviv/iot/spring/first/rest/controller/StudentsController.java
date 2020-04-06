package ua.lviv.iot.spring.first.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.spring.first.business.StudentService;
import ua.lviv.iot.spring.first.rest.model.Student;

// @RestController is an annotation that marks a class as a request and exception handler. it can be
// replaced with @Controller (has implementation of HTTP-methods and handles requests) and
// @ResponseBody (so that you don't have to put it in each method of the class) annotations.
// @RequestMapping specifies the addresses of the controller's methods where they will be available
// on the website. can be applied to the whole class as well as to the specific method. you can pass
// additional parameters to it, such as an HTTP-method, or specify a query body type:
// e.g. consumes = "application/json" says Content-Type of the request must be "application / json".

@RequestMapping("/students")
@RestController
public class StudentsController {

  // HASHMAP is a map based collection class which stores the data in key+value pairs. it stores
  // the data in RAM, therefore you can't get a student you created in a previous session.
  // we don't use it at this point because we work with a repository now.

  private Map<Integer, Student> students = new HashMap<>();

  // ATOMICINTEGER is used when you can get two or more requests at the same time, and you want to
  // avoid two objects getting the same data (e.g. id). we want to use it instead of ++ because
  // ++ first returns a counter's value and then increments it. if two requests are processed at the
  // same time, it may cause the situation when you lose 1 of 2 objects, and an id point which that
  // object was supposed to take.
  // we don't use it at this point because we have a primary key ID field in Student table.

  private AtomicInteger idCounter = new AtomicInteger();

  // @AUTOWIRED says Spring Boot to find a corresponding bean for this variable so we can treat it
  // as a Spring Boot object.

  @Autowired
  private StudentService studentService;

  // JSON - JS Object Notation. a human-readable text which consists of object's data in a form of
  // attribute-value pair, e.g.: {"firstName":"pedro","lastName":"aldomovar"}
  // @GETMAPPING handles HTTP GET requests matched with given URI expression. used to query
  // the server for specific information
  // @PATHVARIABLE is an annotation used to pass parameters through a query address.

  @GetMapping(path = "/{id}")
  public ResponseEntity<Student> getStudentById(@PathVariable("id") Integer studentId) {
    return studentService.getStudentById(studentId);
  }

  @GetMapping
  public List<Student> getStudents() {
    return studentService.getStudents();
  }

  // @POSTMAPPING - requests the server to store the message body. usually modeled as an insert.
  // the main difference between the POST method and the GET is that the GET carries a query
  // parameter added to the URL string, while POST carries a parameter in the body of the message.
  // @REQUESTBODY - whatever you can get from the request, put it in a request body and transfer it
  // into object, e.g. Student student. it basically DESERIALIZES JSON string into Java object.
  // (serialization is the process of translating data into a format that can be stored, e.g. file)
  // PRODUCES attribute in @POSTMAPPING lets you specify which content type your WebService endpoint
  // can return, e.g. JSON or XML.

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, "application/x-yaml"})
  public Student createStudent(final @RequestBody Student student) {
    return studentService.createStudent(student);
  }

  // @DELETEMAPPING method should not return anything, but we are trying to avoid deleting
  // a non-existing student, so it should return 404 NOT FOUND instead.

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer studentId) {
    return studentService.deleteStudent(studentId);
  }

  // @PUTMAPPING returns a PREVIOUS version of the object, therefore you don't really need to return
  // this object. we want to get an HttpStatus instead.

  @PutMapping(path = "/{id}")
  public ResponseEntity<Student> updateStudent(final @PathVariable("id") Integer studentId,
                               final @RequestBody Student student) {
    return studentService.updateStudent(studentId, student);
  }

  // this is a shortened version of @GETMAPPING and @PUTMAPPING but in one line.

  @RequestMapping(method = {RequestMethod.GET, RequestMethod.PUT}, path = "/smartest")
  public Student getTheSmartestStudent() {
    return new Student("quentin", "karantino");
  }

}
