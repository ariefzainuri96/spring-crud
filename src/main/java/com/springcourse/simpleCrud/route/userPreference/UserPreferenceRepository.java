package com.springcourse.simpleCrud.route.userPreference;

import com.springcourse.simpleCrud.model.schema.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreference, Integer> { }
