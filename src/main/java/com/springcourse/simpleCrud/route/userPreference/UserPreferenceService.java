package com.springcourse.simpleCrud.route.userPreference;

import com.springcourse.simpleCrud.model.BaseResponse;
import com.springcourse.simpleCrud.model.schema.UserPreference;
import com.springcourse.simpleCrud.model.schema.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Service
public class UserPreferenceService {

    @Autowired
    UserPreferenceRepository userPreferenceRepository;

    public UserPreference saveUserPreference(UserPreference userPreference) {
        return userPreferenceRepository.save(userPreference);
    }

    public BaseResponse<UserPreference> updateUserPreference(int id, Map<String, Object> userPreference) {
        try {
            UserPreference _userPreference = userPreferenceRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User preference not found"));

            userPreference.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(UserPreference.class, key);

                if (field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, _userPreference, value);
                }
            });

            userPreferenceRepository.save(_userPreference);

            return new BaseResponse<>(200, "User preference updated successfully", _userPreference);
        } catch (RuntimeException e) {
            return new BaseResponse<>(400, e.getLocalizedMessage(), null);
        }
    }

}
