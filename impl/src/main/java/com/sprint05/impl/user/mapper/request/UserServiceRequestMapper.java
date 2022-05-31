package com.sprint05.impl.user.mapper.request;

import com.sprint05.impl.repository.entity.UserEntity;
import com.sprint05.impl.user.model.request.UserServiceRequest;
import com.sprint05.impl.user.model.response.UserServiceResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserServiceRequestMapper {

    public static UserEntity toUserRequest(UserServiceRequest userServiceRequest) {
        return Optional.ofNullable(userServiceRequest)
                .map(userService -> UserEntity.builder()
                        .id(userService.getId())
                        .name(userService.getName())
                        .playlist(userService.getPlaylist())
                        .build())
                .orElse(null);
    }

    public static UserServiceRequest toUserEntity(UserEntity userEntity) {
        return Optional.ofNullable(userEntity)
                .map(userService -> UserServiceRequest.builder()
                        .id(userService.getId())
                        .name(userService.getName())
                        .playlist(userService.getPlaylist())
                        .build())
                .orElse(null);
    }
}
