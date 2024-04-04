package com.kwangho.account.user;


import com.kwangho.account.Enum.Messege;
import com.kwangho.account.common.ResponseMessege;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class UserContoroller {
    private final UserService service;
    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/user/join")
    public ResponseMessege join(@RequestBody UserRequestDto userRequestDto) {
//        String encodePassword = passwordEncoder.encode(userRequestDto.getPassword());
        if (repo.findByUsername(userRequestDto.getUsername()).isPresent()) {
            return new ResponseMessege("messege", Messege.FAIL);
        } else {
            repo.save(User.builder()
                    .username(userRequestDto.getUsername())
                    .password(userRequestDto.getPassword())
                    .name(userRequestDto.getName())
                    .address(userRequestDto.getAddress())
                    .job(userRequestDto.getJob())
                    .build());
            return new ResponseMessege("messege", Messege.SUCCESS);
        }
    }

    @PostMapping("/user/login")
    public ResponseMessege login(@RequestBody UserRequestDto userRequestDto) {
        User existUser = repo.findByUsername(userRequestDto.getUsername()).orElse(null);
        if (existUser == null) {
            return new ResponseMessege("messege", Messege.FAIL);
//        } else if (passwordEncoder.matches(existUser.getPassword(), userRequestDto.getPassword())) {
        } else if (!existUser.getPassword().equals(userRequestDto.getPassword())) {
            return new ResponseMessege("messege", Messege.WRONG_PASSWORD);
        } else {
            return new ResponseMessege("messege", Messege.SUCCESS);
        }
    }

    @PostMapping("/user/updatePassword")
    public ResponseMessege updatePassword(@RequestBody Map<?, ?> paramap) {
        User existUser = repo.findByUsername((String) paramap.get("username")).orElse(null);
        if (existUser == null) {
            return new ResponseMessege("messege", Messege.FAIL);
//        } else if (passwordEncoder.matches(existUser.getPassword(), (String) paramap.get("password"))) {
        } else if (!existUser.getPassword().equals(paramap.get("password"))) {
            return new ResponseMessege("messege", Messege.WRONG_PASSWORD);
        } else {
            existUser.setPassword((String) paramap.get("newpassword"));
            repo.save(existUser);
            return new ResponseMessege("messege", Messege.SUCCESS);
        }
    }


    @GetMapping("/user/list")
    public List<User> findAll() {
        return service.findAll();
    }


}
