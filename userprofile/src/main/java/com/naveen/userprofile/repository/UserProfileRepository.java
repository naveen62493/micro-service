package com.naveen.userprofile.repository;


import com.naveen.userprofile.bean.UserProfile;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Naveen on 2/8/2018.
 */
public interface UserProfileRepository extends Repository<UserProfile,String> {

    void delete(UserProfile userProfile);

    List<UserProfile> findAll();

    Optional<UserProfile> findOne(String id);

    UserProfile save(UserProfile details);
}
