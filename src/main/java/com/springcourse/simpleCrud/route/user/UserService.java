package com.springcourse.simpleCrud.route.user;

import com.springcourse.simpleCrud.model.BaseResponse;
import com.springcourse.simpleCrud.model.schema.UserPreference;
import com.springcourse.simpleCrud.model.schema.UserProfile;
import com.springcourse.simpleCrud.route.user.jwt.JWTAuthService;
import com.springcourse.simpleCrud.route.user.model.LoginRequest;
import com.springcourse.simpleCrud.route.userPreference.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    UserRepository userRepository;
    UserPreferenceService userPreferenceService;
    AuthenticationManager authenticationManager;
    JWTAuthService jwtAuthService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    @Autowired
    public UserService(AuthenticationManager authenticationManager, JWTAuthService jwtAuthService, UserRepository userRepository, UserPreferenceService userPreferenceService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.userPreferenceService = userPreferenceService;
        this.jwtAuthService = jwtAuthService;
    }

    public BaseResponse<UserProfile> addUser(UserProfile user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return new BaseResponse<>(400, "User already exists", null);
        }

        UserPreference userPreference = userPreferenceService.saveUserPreference(new UserPreference(
                null, false, false
        ));

        // Set user preference to user that we want to return to response
        user.setUserPreference(userPreference);

        // encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return new BaseResponse<>(200, "User added successfully", user);
    }

    public BaseResponse<UserProfile> updateUser(int userId, Map<String, Object> updates) throws RuntimeException {
        UserProfile user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(UserProfile.class, key);

            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, user, value);
            }
        });

        userRepository.save(user);

        return new BaseResponse<>(200, "User updated successfully", user);
    }

    public BaseResponse<UserProfile> getUserById(int id) {
        UserProfile user = userRepository.findById(id).orElse(null);

        return new BaseResponse<>(
                user != null ? 200 : 404,
                String.format("%s", user != null ? "User found" : "User not found"),
                user
        );
    }

    public BaseResponse<List<UserProfile>> getAllUsers() {
        return new BaseResponse<>(200, "All users found", userRepository.findAll());
    }

    public BaseResponse<String> login(LoginRequest request) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        if (authentication.isAuthenticated()) {
            return new BaseResponse<>(200, "Login success", jwtAuthService.generateToken(request.getEmail()));
        }

        throw new Exception("Login failed");
    }
}
