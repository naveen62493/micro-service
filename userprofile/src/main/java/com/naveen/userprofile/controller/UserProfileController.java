package com.naveen.userprofile.controller;

import com.naveen.userprofile.dto.UserProfileDTO;
import com.naveen.userprofile.exception.UserProfileNotFoundException;
import com.naveen.userprofile.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Naveen on 2/8/2018.
 */
@RestController
@RequestMapping("/api/user")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    UserProfileController(UserProfileService userProfileService){
        this.userProfileService = userProfileService;
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public UserProfileDTO create(@RequestBody @Valid UserProfileDTO userProfileDTO){
        return userProfileService.create(userProfileDTO);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public UserProfileDTO delete(@PathVariable("id") String id){
        return userProfileService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserProfileDTO> findAll(){
        return userProfileService.findAll();
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public UserProfileDTO findById(@PathVariable("id") String id){
        return userProfileService.findById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public UserProfileDTO update(@RequestBody @Valid UserProfileDTO todoEntry) {
        return userProfileService.update(todoEntry);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleTodoNotFound(UserProfileNotFoundException ex) {
    }


}
