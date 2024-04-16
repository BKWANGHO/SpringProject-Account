package com.kwangho.account.user.service;

import com.kwangho.account.common.component.Messenger;
import com.kwangho.account.common.service.CommandService;
import com.kwangho.account.common.service.QueryService;
import com.kwangho.account.user.model.User;
import com.kwangho.account.user.model.UserDto;
import org.apache.catalina.UserDatabase;

import java.util.List;

public interface UserService extends CommandService<UserDto>, QueryService<UserDto> {


    Messenger updatePassword(UserDto dto);

    Messenger login(UserDto userRequestDto);
}
