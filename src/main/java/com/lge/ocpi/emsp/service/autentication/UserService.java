package com.lge.ocpi.emsp.service.autentication;



import com.lge.ocpi.emsp.model.dto.userDetails.UserRequest;
import com.lge.ocpi.emsp.model.dto.userDetails.UserResponse;

import java.util.List;


public interface UserService {

    UserResponse saveUser(UserRequest userRequest);

    UserResponse getUser();
    UserResponse getUserByID(String username);

    List<UserResponse> getAllUser();


}
