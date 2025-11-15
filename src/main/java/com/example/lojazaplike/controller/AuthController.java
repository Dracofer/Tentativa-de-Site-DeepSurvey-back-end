
package com.example.lojazaplike.controller;

import com.example.lojazaplike.model.User;
import com.example.lojazaplike.model.Role;
import com.example.lojazaplike.repository.UserRepository;
import com.example.lojazaplike.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Set;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        if (userRepository.findByUsername(username).isPresent()) {
            return Map.of("error", "username_taken");
        }
        User u = new User();
        u.setUsername(username);
        u.setPassword(passwordEncoder.encode(password));
        u.setRoles(Set.of(Role.ROLE_USER));
        userRepository.save(u);
        String token = jwtUtil.generateToken(username);
        return Map.of("token", token, "username", username);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        var opt = userRepository.findByUsername(username);
        if (opt.isEmpty()) return Map.of("error", "invalid_credentials");
        User u = opt.get();
        if (!passwordEncoder.matches(password, u.getPassword())) return Map.of("error", "invalid_credentials");
        String token = jwtUtil.generateToken(username);
        return Map.of("token", token, "username", username);
    }
}
