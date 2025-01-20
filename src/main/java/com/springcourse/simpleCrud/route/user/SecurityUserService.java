package com.springcourse.simpleCrud.route.user;

import com.springcourse.simpleCrud.model.schema.UserProfile;
import com.springcourse.simpleCrud.route.userPreference.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserService implements UserDetailsService {

    UserRepository userRepository;
    UserPreferenceService userPreferenceService;

    @Autowired
    public SecurityUserService(UserRepository userRepository, UserPreferenceService userPreferenceService) {
        this.userRepository = userRepository;
        this.userPreferenceService = userPreferenceService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrincipal(user);
    }
}
