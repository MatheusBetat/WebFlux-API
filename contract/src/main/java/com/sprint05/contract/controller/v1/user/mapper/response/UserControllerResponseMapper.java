package com.sprint05.contract.controller.v1.user.mapper.response;

import com.sprint05.contract.controller.v1.user.model.response.UserControllerResponse;
import com.sprint05.impl.user.model.response.UserServiceResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserControllerResponseMapper {

    public static UserControllerResponse mapToResponse(UserServiceResponse userServiceResponse) {
        return UserControllerResponse.builder()
                .id(userServiceResponse.getId())
                .name(userServiceResponse.getName())
                .playlist(userServiceResponse.getPlaylist())
                .build();

    }
}
