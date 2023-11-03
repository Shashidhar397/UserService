package com.messanger.userService.repository;

import com.messanger.userService.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    public User findUserByContactNumber(String contactNumber);

}
