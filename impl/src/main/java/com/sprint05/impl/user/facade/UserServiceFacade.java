package com.sprint05.impl.user.facade;

import com.sprint05.impl.moviedb.facade.MoviedbServiceFacade;
import com.sprint05.impl.user.mapper.response.UserServiceResponseMapper;
import com.sprint05.impl.user.model.request.UserServiceRequest;
import com.sprint05.impl.user.model.response.UserServiceResponse;
import com.sprint05.impl.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.sprint05.impl.moviedb.mapper.response.MoviedbServiceResponseMapper.mapToMoviedbEntity;
import static com.sprint05.impl.user.mapper.request.UserServiceRequestMapper.toUserEntity;

@Component
@AllArgsConstructor
public class UserServiceFacade {

    private final UserService userService;
    private final MoviedbServiceFacade moviedbServiceFacade;

    public Mono<UserServiceResponse> setMyMovie(String id, String movieId){

        return moviedbServiceFacade.getMyMovie(movieId)
                .zipWith(userService.getUser(id).map(UserServiceResponseMapper::mapToUserEntity))
                .flatMap(tuple -> {
                    tuple.getT2().getPlaylist().add(mapToMoviedbEntity(tuple.getT1()));
                     return userService.saveUser(toUserEntity(tuple.getT2()))
                             .map(userServiceResponse -> UserServiceResponseMapper.toServiceResponse(tuple.getT2()));
                });
    }

    public Mono<UserServiceResponse> getUser(String id) {
        return userService.getUser(id);
    }

    public Flux<UserServiceResponse> getAllUsers() {
        return userService.getAll();
    }

    public Mono<Void> deleteUsersByIds(List<String> ids){
        return userService.delete(ids);

    }

    public Mono<UserServiceResponse> saveUser(UserServiceRequest userServiceRequest){
        return userService.saveUser(userServiceRequest);

    }
}












