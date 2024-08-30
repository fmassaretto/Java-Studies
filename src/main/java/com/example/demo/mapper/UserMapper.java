package com.example.demo.mapper;

import com.example.demo.model.dto.requests.UserRequest;
import com.example.demo.model.dto.responses.UserResponse;
import com.example.demo.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    List<UserResponse> mapListUserToListUserResponse(List<User> users);
    @Mapping(target = "uuid", source = "userUUID")
    UserResponse mapEntityToUserResponse(User user);
    User mapUserRequestToUser(UserRequest user);
}
