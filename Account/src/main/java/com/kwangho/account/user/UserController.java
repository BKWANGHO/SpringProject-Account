package com.kwangho.account.user;


import com.kwangho.account.common.component.Messenger;
import com.kwangho.account.user.model.UserDto;
import com.kwangho.account.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class UserController {
    private final UserService service;


    @PostMapping( "/save")
    public ResponseEntity<Messenger> save(@RequestBody UserDto dto) {
        log.info("입력받은 정보 : {}",dto);
        return ResponseEntity.ok(service.save(dto));
    }

    @DeleteMapping( "/delete")
    public ResponseEntity<Messenger> deleteById(@RequestParam Long id) {
        log.info("입력받은 정보 : {}",id);
        return ResponseEntity.ok(service.deleteById(id));
    }

    @PutMapping( "/modify")
    public ResponseEntity<Messenger> modify(@RequestBody UserDto dto) {
        log.info("입력받은 정보 : {}",dto);
        return ResponseEntity.ok(service.modify(dto));
    }


    @GetMapping( "/detail")
    public ResponseEntity<Optional<UserDto>> findById(@RequestParam Long id) {
        log.info("입력받은 정보 : {}", id);
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping( "/count")
    public ResponseEntity<Long> count() {
        log.info("입력받은 정보 : {}");
        return ResponseEntity.ok(service.count());
    }

    @GetMapping( "/exists")
    public ResponseEntity<Boolean> existsById(Long id) {
        log.info("입력받은 정보 : {}");
        return ResponseEntity.ok(service.existsById(id));
    }

    @PostMapping("/login")
    public ResponseEntity<Messenger> login(@RequestBody UserDto dto) {
        return ResponseEntity.ok(service.login(dto));
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<Messenger> updatePassword(@RequestBody UserDto dto) {
        return ResponseEntity.ok(service.updatePassword(dto));
    }


    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

}
