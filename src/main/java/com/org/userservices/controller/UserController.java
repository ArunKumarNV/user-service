package com.org.userservices.controller;

import com.org.userservices.entity.User;
import com.org.userservices.service.UserService;
import com.org.userservices.service.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable("id") Long userId){
        ResponseDTO responseDTO = userService.getUser(userId);

        return ResponseEntity.ok(responseDTO);
    }
}
