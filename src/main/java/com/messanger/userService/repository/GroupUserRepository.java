package com.messanger.userService.repository;

import com.messanger.userService.models.entity.Group;
import com.messanger.userService.models.entity.GroupUser;
import com.messanger.userService.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author shashidhar
 */
@Repository
public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {
    Optional<GroupUser> findByGroupAndUser(Group group, User user);
}
