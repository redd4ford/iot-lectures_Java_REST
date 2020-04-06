package ua.lviv.iot.spring.first.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.spring.first.dataaccess.GroupRepository;
import ua.lviv.iot.spring.first.rest.model.Group;
import java.util.List;

// we use Services to increase the code's readability, but basically all it does is operates
// the repository of groups.

@Service
public class GroupService {
  @Autowired
  private GroupRepository groupRepository;

  public List<Group> getGroups() {
    return groupRepository.findAll();
  }
}
