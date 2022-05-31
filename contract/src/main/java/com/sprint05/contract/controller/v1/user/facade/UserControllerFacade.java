package com.sprint05.contract.controller.v1.user.facade;

import com.sprint05.contract.controller.v1.user.mapper.response.UserControllerResponseMapper;
import com.sprint05.contract.controller.v1.user.model.request.UserControllerRequest;
import com.sprint05.contract.controller.v1.user.model.response.UserControllerResponse;
import com.sprint05.impl.user.facade.UserServiceFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.sprint05.contract.controller.v1.user.mapper.request.UserControllerRequestMapper.mapperUserToService;

@Component
@AllArgsConstructor
public class UserControllerFacade {

    private final UserServiceFacade userServiceFacade;

    public Mono<UserControllerResponse> getUser(String id) {
        return userServiceFacade.getUser(id)
                .map(UserControllerResponseMapper::mapToResponse);
    }

    public Flux<UserControllerResponse> getAllUser() {
        return userServiceFacade.getAllUsers()
                .map(UserControllerResponseMapper::mapToResponse);

    }

    public Mono<Void> deleteUserByIds(List<String> ids){
        return userServiceFacade.deleteUsersByIds(ids);

    }

    public Mono<UserControllerResponse> saveUser(UserControllerRequest userControllerRequest){
        return userServiceFacade.saveUser(mapperUserToService(userControllerRequest))
                .map(UserControllerResponseMapper::mapToResponse);

    }

    public Mono<UserControllerResponse> setMyMovie(String id, String movieId) {
        return userServiceFacade.setMyMovie(id, movieId)
                .map(UserControllerResponseMapper::mapToResponse);

    }
}
