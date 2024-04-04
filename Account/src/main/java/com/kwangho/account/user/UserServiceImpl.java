package com.kwangho.account.user;


import com.kwangho.account.common.ResponseMessege;
import com.kwangho.account.dto.request.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository repo;


    @Override
    public List<User> findAll() {
        return repo.findAll();
    }
}
