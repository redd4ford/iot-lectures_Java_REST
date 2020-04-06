package ua.lviv.iot.spring.first.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.spring.first.rest.model.Group;

// JpaRepository is like CrudRepository, but CrudRepository is an extension. it provides operations
// for saving, returning, searching, updating, and deleting the data for the class Group.

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
}
