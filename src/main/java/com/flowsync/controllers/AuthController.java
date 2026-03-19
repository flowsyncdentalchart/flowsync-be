package com.flowsync.controllers;

import com.flowsync.dto.AuthRequest;
import com.flowsync.dto.AuthResponse;
import com.flowsync.models.User;
import com.flowsync.repositories.UserRepository;
import com.flowsync.services.AuthService;
import com.flowsync.utils.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AuthService authService;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, AuthService authService,
            UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String jwt = jwtUtil.generateToken(userDetails);

            ResponseCookie jwtCookie = ResponseCookie.from("jwt", jwt).httpOnly(true).secure(false).path("/")
                    .maxAge(10 * 60 * 60).sameSite("Lax").build();

            User user = userRepository.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            AuthResponse authResponse = new AuthResponse(jwt, "36000", "Bearer", "Login successful!");

            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(authResponse);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
        }
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated!");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = authService.findByUsername(userDetails.getUsername());

        return ResponseEntity.ok("" + user.getUsername() + ", " + user.getFirstName() + " " + user.getLastName());
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        ResponseCookie jwtCookie = ResponseCookie.from("jwt", "").httpOnly(true).secure(false).path("/").maxAge(0)
                .sameSite("Strict").build();

        SecurityContextHolder.clearContext();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body("Logout successful!");
    }
}
