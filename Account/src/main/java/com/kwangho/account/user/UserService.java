package com.kwangho.account.user;

import com.kwangho.account.common.ResponseMessege;
import com.kwangho.account.dto.request.UserRequestDto;

import java.util.List;

public interface UserService {
    List<User> findAll();
}
