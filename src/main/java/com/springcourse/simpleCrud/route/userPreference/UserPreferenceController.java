package com.springcourse.simpleCrud.route.userPreference;

import com.springcourse.simpleCrud.model.BaseResponse;
import com.springcourse.simpleCrud.model.schema.UserPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserPreferenceController {

    @Autowired
    UserPreferenceService userPreferenceService;

    @PatchMapping("/userPreference/{id}")
    public BaseResponse<UserPreference> updateUserPreference(@PathVariable int id, @RequestBody Map<String, Object> userPreference) {
        return userPreferenceService.updateUserPreference(id, userPreference);
    }
}
