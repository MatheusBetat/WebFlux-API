package com.sprint05.impl.user.service;

import com.sprint05.impl.repository.UserRepository;
import com.sprint05.impl.user.mapper.response.UserServiceResponseMapper;
import com.sprint05.impl.user.model.request.UserServiceRequest;
import com.sprint05.impl.user.model.response.UserServiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.sprint05.impl.user.mapper.request.UserServiceRequestMapper.toUserRequest;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Flux<UserServiceResponse> getAll(){
        return userRepository.findAll()
                .map(UserServiceResponseMapper::toServiceResponse);
    }

    public Mono<UserServiceResponse> getUser(String id){
        return userRepository.findById(id)
                .map(UserServiceResponseMapper::toServiceResponse);
    }

    public Mono<UserServiceResponse> saveUser(UserServiceRequest userServiceRequest){
        return userRepository.save(toUserRequest(userServiceRequest))
                .map((UserServiceResponseMapper::toServiceResponse));

    }

    public Mono<Void> delete(List<String> ids) {
        return userRepository.deleteAllById(ids);

    }
}
