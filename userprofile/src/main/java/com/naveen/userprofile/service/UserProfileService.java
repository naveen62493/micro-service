package com.naveen.userprofile.service;


import com.naveen.userprofile.dto.UserProfileDTO;

import java.util.List;

/**
 * Created by Naveen on 2/8/2018.
 */
public interface UserProfileService {

    UserProfileDTO create(UserProfileDTO userProfileDTO);

    UserProfileDTO delete(String id);

    List<UserProfileDTO> findAll();

    UserProfileDTO findById(String id);

    UserProfileDTO update(UserProfileDTO userProfileDTO);


}
