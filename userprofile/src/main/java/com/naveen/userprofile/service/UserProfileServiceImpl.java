package com.naveen.userprofile.service;

import com.naveen.userprofile.bean.UserProfile;
import com.naveen.userprofile.dto.UserProfileDTO;
import com.naveen.userprofile.exception.UserProfileNotFoundException;
import com.naveen.userprofile.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by Naveen on 2/8/2018.
 */
@Service
public class UserProfileServiceImpl implements UserProfileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileServiceImpl.class);


    private final UserProfileRepository userProfileRepository;

    @Autowired
    UserProfileServiceImpl(UserProfileRepository userProfileRepository){
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserProfileDTO create(UserProfileDTO userProfileDTO) {

        LOGGER.info("Creating a new UserProfile entry with information : {}", userProfileDTO);

        UserProfile userProfile = UserProfile.getUserDetailBuilder()
                .setFirstName(userProfileDTO.getFisrtName())
                .setLastName(userProfileDTO.getLastName())
                .setAge(userProfileDTO.getAge()).build();

        userProfile = userProfileRepository.save(userProfile);

        LOGGER.info("Created a new UserProfile entry with information: {}", userProfile);

        return convertToDTO(userProfile);
    }



    @Override
    public UserProfileDTO delete(String id) {
        LOGGER.info("Deleting a UserProfile entry with id: {}",id);

        UserProfileDTO userProfileDTO = findById(id);
        userProfileRepository.delete(convertToEntity(userProfileDTO));

        LOGGER.info("Deleted a UserProfile entry with id: {}",id);

        return userProfileDTO;

    }

    @Override
    public List<UserProfileDTO> findAll() {
        LOGGER.info("Finding all UserProfile entries.");

        List<UserProfile> todoEntries = userProfileRepository.findAll();

        LOGGER.info("Found {} userProfile entries", todoEntries.size());

        return convertToDTOs(todoEntries);
    }

    private List<UserProfileDTO> convertToDTOs(List<UserProfile> models) {
        return models.stream()
                .map(this::convertToDTO)
                .collect(toList());
    }

    @Override
    public UserProfileDTO findById(String id) {
        Optional<UserProfile> result = userProfileRepository.findOne(id);
        return result.map(this::convertToDTO).orElseThrow(() -> new UserProfileNotFoundException(id));
    }

    @Override
    public UserProfileDTO update(UserProfileDTO userProfileDTO) {
        LOGGER.info("Updating User Profile entry with information: {}", userProfileDTO);

        UserProfileDTO updatedDTO = findById(userProfileDTO.getId());
        UserProfile updatedUserProfile = convertToEntity(updatedDTO);
        updatedUserProfile.update(userProfileDTO.getFisrtName(), userProfileDTO.getLastName(),userProfileDTO.getAge());
        updatedUserProfile = userProfileRepository.save(updatedUserProfile);

        LOGGER.info("Updated User Profile entry with information: {}", updatedUserProfile);

        return convertToDTO(updatedUserProfile);
    }

    private UserProfileDTO convertToDTO(UserProfile userProfile) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();

        userProfileDTO.setId(userProfile.getId());
        userProfileDTO.setFisrtName(userProfile.getFirstName());
        userProfileDTO.setLastName(userProfile.getLastName());
        userProfileDTO.setAge(userProfile.getAge());

        return userProfileDTO;
    }

    private UserProfile convertToEntity(UserProfileDTO userProfileDTO){

        UserProfile userProfile = UserProfile.getUserDetailBuilder()
                .setFirstName(userProfileDTO.getFisrtName())
                .setLastName(userProfileDTO.getLastName())
                .setAge(userProfileDTO.getAge()).build();
        return userProfile;

    }
}
