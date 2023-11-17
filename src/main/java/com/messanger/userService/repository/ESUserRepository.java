package com.messanger.userService.repository;

import com.messanger.userService.models.entity.es.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author shashidhar
 */
@Repository
public interface ESUserRepository extends ElasticsearchRepository<User, Long> {

    List<User> findByUuid(String uuids);

    List<User> findByUuidIn(Collection<String> uuids);
}
