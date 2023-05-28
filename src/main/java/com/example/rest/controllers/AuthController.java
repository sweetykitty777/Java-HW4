package com.example.rest.controllers;

import com.example.rest.configs.JwtGenerator;
import com.example.rest.models.User;
import com.example.rest.repositories.UserRepository;
import com.example.rest.requests.LoginRequest;
import com.example.rest.requests.SignupRequest;
import com.example.rest.responses.JwtResponse;
import com.example.rest.responses.MessageResponse;
import com.example.rest.services.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtGenerator jwtGenerator;

    /**
     * Метод для авторизации пользователя
     * @param loginRequest данные для входа
     * @return в случае успеха - токен, информация о пользователе, иначе - инфо об ошибке
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtGenerator.generateJwtToken(authentication);

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return  ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    /**
     * Метод для регистрации пользователя
     * @param signupRequest данные для регистрации
     * @return Сообщение об успешной регистрации/инфо об ошибке
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        if (signupRequest.getEmail() == null || !correctEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("No email"));
        }
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email exists"));
        }

        if (signupRequest.getUsername() == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("No username"));
        }
        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());
        if (encodedPassword == null) {
            encodedPassword = "1234";
        }
       User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(), encodedPassword, "customer");

        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User was registered"));
    }

    Boolean correctEmail(String a) {
        return a.contains("@");
    }
}
