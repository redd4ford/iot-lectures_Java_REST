package ua.lviv.iot.spring.first.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.spring.first.business.SubjectService;
import ua.lviv.iot.spring.first.rest.model.Subject;
import java.util.List;

// @RestController is an annotation that marks a class as a request and exception handler. it can be
// replaced with @Controller (has implementation of HTTP-methods and handles requests) and
// @ResponseBody (so that you don't have to put it in each method of the class) annotations.
// @RequestMapping specifies the addresses of the controller's methods where they will be available
// on the website. can be applied to the whole class as well as to the specific method. you can pass
// additional parameters to it, such as an HTTP-method, or specify a query body type:
// e.g. consumes = "application/json" says Content-Type of the request must be "application / json".

@RequestMapping("/subjects")
@RestController
public class SubjectController {

  // @AUTOWIRED says Spring Boot to find a corresponding bean for this variable so we can treat it
  // as a Spring Boot object.

  @Autowired
  private SubjectService subjectService;

  // JSON - JS Object Notation. a human-readable text which consists of object's data in a form of
  // attribute-value pair, e.g.: {"firstName":"pedro","lastName":"aldomovar"}
  // @GETMAPPING handles HTTP GET requests matched with given URI expression. used to query
  // the server for specific information
  // @PATHVARIABLE is an annotation used to pass parameters through a query address.

  @GetMapping
  public List<Subject> getSubjects() {
    return subjectService.getSubjects();
  }

}   