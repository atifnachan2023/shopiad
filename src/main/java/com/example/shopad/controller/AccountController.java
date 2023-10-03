package com.example.shopad.controller;

import com.example.shopad.exceptions.UserException;
import com.example.shopad.model.UserAccount;
import com.example.shopad.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {


    Logger logger= LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String doTest(){
        return "end-points-running";
    }

    @PostMapping("/user")
    public ResponseEntity<UserAccount> createUser(@RequestBody UserAccount userAccount){

        //Checking if Mandatory Fields are null or empty
        if (userAccount.getFirstName()==null||userAccount.getLastName()==null||userAccount.getFirstName().isEmpty()||userAccount.getLastName().isEmpty()){

            throw new UserException("FirstName ,LastName ,username ,emailId  cannot be null");

        }

            if (userAccount.getUserName() == null || userAccount.getEmailId() == null || userAccount.getUserName().isEmpty()||userAccount.getEmailId().isEmpty()) {

                throw new UserException("FirstName ,LastName ,username ,emailId  cannot be null");
            }

            logger.info("all parameters are passed correctly");
try {
    userAccount.setCreatedAt(Instant.now());
    userAccount.setUpdatedAt(Instant.now());
    userService.createUser(userAccount);
    logger.info("Account created Successfully");
    return ResponseEntity.status(HttpStatus.CREATED).body(userAccount);
}
catch (Exception e){

    e.printStackTrace();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

}

    }

    @GetMapping("/users")
    public ResponseEntity<List<UserAccount>> getAllUsers(){


        List<UserAccount> userAccounts =new ArrayList<>();

        userAccounts=userService.getUsers();

        return  ResponseEntity.status(HttpStatus.OK).body(userAccounts);

    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<UserAccount> getUser(@PathVariable int userId){

        UserAccount userAccount=userService.getUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userAccount);

    }



}
