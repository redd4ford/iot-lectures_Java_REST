package ua.lviv.iot.spring.first.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.Set;

// @Entity shows that the class can be mapped to a corresponding table

@Entity
public class Subject {

  // these annotations tell Spring boot to increment an id field in the database every time we
  // create a new object.

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String name;

  // @JOINTABLE says that we need an intermediate table between Subject and Student to connect them.
  // JOINCOLUMNS are which columns connect two tables. we must match that they're NOT nullable,
  // they're ids after all.
  // @JSONIGNOREPROPERTIES annotation is used to either ban serialization of properties
  // (during serialization), or ignore processing of JSON properties read (during DEserialization).

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "Student_Subjects",
             joinColumns = { @JoinColumn (name = "subject_id", nullable = false)},
             inverseJoinColumns = { @JoinColumn (name = "user_id", nullable = false)})
  @JsonIgnoreProperties("subjects")
  private Set<Student> students;

  public Subject() {
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

  public Set<Student> getStudents() {
    return students;
  }

  public void setStudents(Set<Student> students) {
    this.students = students;
  }

}
