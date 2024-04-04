package com.kwangho.account.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository repo;


    @Override
    public List<User> findAll() {
        return repo.findAll();
    }
}
