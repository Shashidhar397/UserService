package com.messanger.userService.service.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.TextQueryType;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.messanger.userService.models.entity.es.User;
import com.messanger.userService.models.response.GetUsersResponseModel;
import com.messanger.userService.models.response.SearchUserResponseModel;
import com.messanger.userService.models.response.UserResponseModel;
import com.messanger.userService.repository.ESUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @author shashidhar
 */
@Service
public class ESUserServiceImpl implements ESUserService {

    @Autowired
    private ESUserRepository esUserRepository;

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Override
    public void saveUser(UserResponseModel userResponseModel) {
        this.esUserRepository.save(new User(userResponseModel.getUserName(), userResponseModel.getEmail(), userResponseModel.getUuid()));
    }

    @Override
    public List<SearchUserResponseModel> suggestUsers(String searchTerm) {
        List<SearchUserResponseModel> searchUserResponseModels = new ArrayList<>();
        try {
            SearchResponse<User> search = elasticsearchClient.search(s -> s
                            .index("user")
                            .query(q -> q.multiMatch(m -> m.query(searchTerm).fields("name", "email").type(TextQueryType.BestFields))),
                    User.class);

            for (Hit<User> hit: search.hits().hits()) {
                if(hit.source() != null) {
                    searchUserResponseModels.add(new SearchUserResponseModel(hit.source().getName(), hit.source().getEmail(), hit.source().getUuid()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return results as needed
        return searchUserResponseModels;
    }

    @Override
    public GetUsersResponseModel getUsers(List<String> uuids) {
        Map<String, UserResponseModel> usersMap= new HashMap<>();
        try {
            List<User> users = esUserRepository.findByUuidIn(uuids);

            if(users != null && users.size() > 0) {
                users.forEach(user -> {
                    usersMap.put(user.getUuid(), new UserResponseModel(user.getUuid(), user.getName(), user.getEmail()));
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new GetUsersResponseModel(usersMap);
    }
}
