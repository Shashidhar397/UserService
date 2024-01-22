package com.messanger.userService.repository;

import com.messanger.userService.models.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author shashidhar
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findByUuid(String uuid);
}
