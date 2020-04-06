package ua.lviv.iot.spring.first.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

// @Entity shows that the class can be mapped to a corresponding table

@Entity
public class Student {

  // these annotations tell Spring boot to increment an id field in the database every time we
  // create a new object.

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column
  private String firstName;
  @Column
  private String lastName;

  // this is how you connect two tables. MANYtoONE - MANY students can be in ONE group.
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

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name="group_id")
  @JsonIgnoreProperties("students")
  private Group group;

  public Student() {
  }

  public Student(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getId() {
        return id;
    }

  public void setId(Integer id) {
        this.id = id;
    }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }
}
