package com.company.user.management.service;


import com.company.user.management.exception.DataNotFoundException;
import com.company.user.management.model.entity.User;
import com.company.user.management.model.request.UserRegisterRequest;
import com.company.user.management.repository.UserRepository;
import com.company.user.management.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public User registerUser(UserRegisterRequest ur) {
        User user = new User();
        user.set
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        return userRepository.save(user);
    }

    public User findByUsername(String username) throws DataNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(DataNotFoundException::new);
    }

    public ResponseEntity<String> login(User user) throws DataNotFoundException {
        User foundUser = findByUsername(user.getUsername());
        if (foundUser != null && new BCryptPasswordEncoder().matches(user.getPassword(), foundUser.getPassword())) {
            String token = jwtUtil.generateToken(foundUser.getUsername());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
