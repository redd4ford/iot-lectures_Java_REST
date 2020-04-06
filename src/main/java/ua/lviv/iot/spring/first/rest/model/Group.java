package ua.lviv.iot.spring.first.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.Set;

// to escape SQL reserved words use @Table annotations and take the group's name in "".

@Entity
@Table(name="\"group\"")
public class Group {

  // these annotations tell Spring boot to increment an id field in the database every time we
  // create a new object.

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Integer id;
  @Column
  private String name;
  @Column
  private Integer enrollmentYear;

  // this is how you connect two tables. ONEtoMANY - ONE group can have MANY students.
  // EAGER loading of collections means that they are fetched fully at the time their parent
  // is fetched. while EAGER loading, then all my child are fetched.
  // CASCADETYPE.ALL means it will do these actions:
  // CASCADETYPE.PERSIST: when persisting an entity, also persist the entities held in this field.
  // if the EntityManager finds a field that references a new entity during flush, and the field
  // does not use CascadeType.PERSIST, it is an error.
  // CASCADETYPE.REMOVE: when deleting an entity, also delete the entities held in this field.
  // CASCADETYPE.REFRESH: when refreshing an entity, also refresh the entities held in this field.
  // CASCADETYPE.MERGE: when merging entity state, also merge the entities held in this field.
  // @JSONIGNOREPROPERTIES annotation is used to either ban serialization of properties
  // (during serialization), or ignore processing of JSON properties read (during DEserialization).

  @OneToMany(mappedBy = "group", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("group")
  public Set<Student> students;
  
  public Group() {
  }

  public Group(String name, Integer enrollmentYear) {
    this.name = name;
    this.enrollmentYear = enrollmentYear;
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
