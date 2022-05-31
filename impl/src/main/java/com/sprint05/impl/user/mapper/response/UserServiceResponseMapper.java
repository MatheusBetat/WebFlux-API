package com.sprint05.impl.user.mapper.response;

import com.sprint05.impl.repository.entity.UserEntity;
import com.sprint05.impl.user.model.response.UserServiceResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserServiceResponseMapper {

    public static UserServiceResponse toServiceResponse(UserEntity userEntity) {
        return UserServiceResponse.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .playlist(userEntity.getPlaylist())
                .build();
    }

    public static UserEntity mapToUserEntity(UserServiceResponse userServiceResponse) {
        return UserEntity.builder()
                .id(userServiceResponse.getId())
                .name(userServiceResponse.getName())
                .playlist(userServiceResponse.getPlaylist())
                .build();
    }

}
