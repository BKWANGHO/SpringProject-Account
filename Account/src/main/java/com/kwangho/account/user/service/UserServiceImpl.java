package com.kwangho.account.user.service;


import com.kwangho.account.common.Enum.Messege;
import com.kwangho.account.common.component.Messenger;
import com.kwangho.account.user.model.User;
import com.kwangho.account.user.model.UserDto;
import com.kwangho.account.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Messenger save(UserDto userDto) {
        String encodePassword = passwordEncoder.encode(userDto.getPassword());
        if (repository.findByUsername(userDto.getUsername()).isPresent()) {
            return Messenger.builder()
                    .message("FAIURE")
                    .build();
        } else {
            User user = repository.save(User.builder()
                    .username(userDto.getUsername())
                    .password(encodePassword)
                    .name(userDto.getName())
                    .address(userDto.getAddress())
                    .job(userDto.getJob())
                    .build());
            return Messenger.builder()
                    .message(user instanceof User ? "SUCCESS":"FAIURE")
                    .build();
        }
    }

    @Override
    public Messenger deleteById(Long id) {
        return null;
    }

    @Override
    public Messenger modify(UserDto userDto) {
        return null;
    }

    @Override
    public List<UserDto> findAll() {
        return null;
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Messenger updatePassword(UserDto dto) {
        User existUser = repository.findByUsername((String) dto.get("username")).orElse(null);
        if (existUser == null) {
            return new ResponseMessege("messege", Messege.FAIL);
//        } else if (passwordEncoder.matches(existUser.getPassword(), (String) paramap.get("password"))) {
        } else if (!existUser.getPassword().equals(dto.get("password"))) {
            return new ResponseMessege("messege", Messege.WRONG_PASSWORD);
        } else {
            existUser.setPassword((String) dto.get("newpassword"));
            repository.save(existUser);
            return new ResponseMessege("messege", Messege.SUCCESS);
        }

    }

    @Override
    public Messenger login(UserDto userRequestDto) {
        User existUser = repository.findByUsername(userRequestDto.getUsername()).orElse(null);
        if (existUser == null) {
            return new ResponseMessege("messege", Messege.FAIL);
//        } else if (passwordEncoder.matches(existUser.getPassword(), userRequestDto.getPassword())) {
        } else if (!existUser.getPassword().equals(userRequestDto.getPassword())) {
            return new ResponseMessege("messege", Messege.WRONG_PASSWORD);
        } else {
            return new ResponseMessege("messege", Messege.SUCCESS);
        }    }
}
