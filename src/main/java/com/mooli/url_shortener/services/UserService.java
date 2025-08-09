package com.mooli.url_shortener.services;

import com.mooli.url_shortener.dtos.LoginDto;
import com.mooli.url_shortener.dtos.RegisterDto;
import com.mooli.url_shortener.dtos.UserDto;
import com.mooli.url_shortener.entities.User;
import com.mooli.url_shortener.repositories.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDto register(RegisterDto registerDto) {
        String username = registerDto.username();
        String emailAddress = registerDto.emailAddress();

        if (userRepository.existsByUsernameOrEmailAddress(username,  emailAddress)) {
            throw new IllegalArgumentException("Username or email address already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(passwordEncoder.encode(registerDto.password()));
        user.setEmailAddress(emailAddress);

        userRepository.save(user);

        return new UserDto(username, emailAddress);
    }

    @Transactional
    public UserDto login(LoginDto loginDto) {
        String userOrEmail = loginDto.usernameOrEmailAddress();

        User user = userRepository.findUsersByUsernameOrEmailAddress(userOrEmail, userOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Username or email address not found"));

        if (!passwordEncoder.matches(loginDto.password(), user.getPasswordHash())){
            throw new BadCredentialsException("Password is wrong");
        }

        return new UserDto(user.getUsername(), user.getEmailAddress());
    }

    @Transactional(readOnly = true)
    public List<User> listEntities() {
        return userRepository.findAll();
    }

}
