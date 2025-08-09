package com.mooli.url_shortener.controllers;

import com.mooli.url_shortener.dtos.LoginDto;
import com.mooli.url_shortener.dtos.RegisterDto;
import com.mooli.url_shortener.dtos.UserDto;
import com.mooli.url_shortener.entities.User;
import com.mooli.url_shortener.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(userService.register(registerDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(userService.login(loginDto));
    }

    @GetMapping("/database")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.listEntities());
    }
}
