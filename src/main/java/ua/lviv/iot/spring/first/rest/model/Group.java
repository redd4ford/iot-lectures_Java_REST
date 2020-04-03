package ua.lviv.iot.spring.first.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

// to escape SQL reserved words use @Table annotations and take the group's name in ""

@Entity(name="groups")
@Table(name="\"group\"")
public class Group {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Integer id;

  private String name;
  private Integer enrollmentYear;

  @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
  @JsonIgnoreProperties("group")
  public Set<Student> students;

  public Group() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getEnrollmentYear() {
    return enrollmentYear;
  }

  public void setEnrollmentYear(Integer enrollmentYear) {
    this.enrollmentYear = enrollmentYear;
  }

  public Set<Student> getStudents() {
    return students;
  }

  public void setStudents(Set<Student> students) {
    this.students = students;
  }
}
