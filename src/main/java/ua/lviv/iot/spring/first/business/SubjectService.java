package ua.lviv.iot.spring.first.business;

// we use Services to increase the code's readability, but basically all it does is operates
// the repository of students.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.spring.first.dataaccess.SubjectRepository;
import ua.lviv.iot.spring.first.rest.model.Subject;
import java.util.List;

@Service
public class SubjectService {
  @Autowired
  private SubjectRepository subjectRepository;

  public List<Subject> getSubjects() {
    return subjectRepository.findAll();
  }

}
