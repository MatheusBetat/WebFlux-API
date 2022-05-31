package com.sprint05.contract.controller.v1.user.mapper.request;

import com.sprint05.contract.controller.v1.user.model.request.UserControllerRequest;
import com.sprint05.impl.user.model.request.UserServiceRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserControllerRequestMapper {

    public static UserServiceRequest mapperUserToService(UserControllerRequest userControllerRequest) {
        return Optional.ofNullable(userControllerRequest)
                .map(userController -> UserServiceRequest.builder()
                        .name(userController.getName())
                        .playlist(userController.getPlaylist())
                        .build())
                .orElse(null);

    }
}
