package com.example.shopad.controller;

import com.example.shopad.exceptions.UserException;
import com.example.shopad.model.StoreInfo;
import com.example.shopad.model.UserAccount;
import com.example.shopad.services.StoreService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/account")
public class AccountController {


    Logger logger= LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private UserService userService;


    @Autowired
    StoreService storeService;

    @GetMapping("/test")
    public String doTest(){
        return "end-points-running";
    }

    // This Api creates new User?Seller Account
    @PostMapping("/user")
    public ResponseEntity<UserAccount> createUser(@RequestBody UserAccount userAccount){

        //Checking if Mandatory Fields are null or empty/Blank
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
//this api fetches all the User/seller Account details
    @GetMapping("/users")
    public ResponseEntity<List<UserAccount>> getAllUsers(){


        List<UserAccount> userAccounts =new ArrayList<>();

        userAccounts=userService.getUsers();

        return  ResponseEntity.status(HttpStatus.OK).body(userAccounts);

    }

//this api fetches User?Seller based on id
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserAccount> getUser(@PathVariable int userId){

        UserAccount userAccount=userService.getUser(userId);http://localhost:8081/account/assign/store/651da220ec94724700e48192/2

        return ResponseEntity.status(HttpStatus.OK).body(userAccount);

    }


//this api assign store to a particular User?Seller
    @PutMapping("/{userId}")
    public ResponseEntity<UserAccount> updateAccount(@RequestBody UserAccount userAccount ,@PathVariable int userId){



        UserAccount userAccount1=userService.updateUser(userAccount,userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(userAccount1);

    }

    //this api fetches particular stores of a particular UserAccount
     @GetMapping("/stores/{userId}")
    public ResponseEntity<List<StoreInfo>> getAllStoreForParticularUserAccount(@PathVariable int userId){

        List<StoreInfo> stores=userService.getStoresForParticularUser(userId);

      return ResponseEntity.status(HttpStatus.OK).body(stores);

    }

    // this api assign user with store based on User/Seller id
 @PutMapping("/assign/store/{storeId}/{userId}")
    public ResponseEntity<UserAccount> assignStoreToUser(@PathVariable String storeId,@PathVariable int userId){

        StoreInfo storeInfo=storeService.getStore(storeId);

        List<UserAccount> existingUserAccountListOfStore=storeInfo.getAccounts();

        List<StoreInfo>storeInfoList1 =new ArrayList<>();

       List<StoreInfo>  storeInfoList2= storeService.getAllUserStores(userId);
        storeInfoList1.add(storeInfo);
        storeInfoList1.addAll(storeInfoList2);

        UserAccount userAccount=userService.getUser(userId);



        userAccount.setStores(storeInfoList1);

        //new part start



     List<UserAccount>userAccountList=new ArrayList<>();
     userAccountList.add(userAccount);
     if(existingUserAccountListOfStore!=null) {
         
         userAccountList.addAll(existingUserAccountListOfStore);
     }
      storeInfo.setAccounts(userAccountList);


// new part ends



        userService.updateUser(userAccount,userId);
        storeService.updateStoreInfo(storeInfo,storeId);






        return ResponseEntity.status(HttpStatus.CREATED).body(userAccount);
    }


    @DeleteMapping("/{userId}/{storeId}")
    public ResponseEntity<UserAccount> deleteStoreFromUser(@PathVariable int userId ,@PathVariable String storeId){

        UserAccount userAccount=userService.getUser(userId);

        List<StoreInfo>storeInfoList=userAccount.getStores();

        List<StoreInfo> removeStore=storeInfoList.stream().filter(a->a.getId().equals(storeId)).collect(Collectors.toList());

        storeInfoList.removeAll(removeStore);

        userAccount.setStores(storeInfoList);

        // UserAccount updated with removal of store of given Id
        userService.updateUser(userAccount,userId);

        StoreInfo storeInfo=storeService.getStore(storeId);

        List<UserAccount>userAccountsList=storeInfo.getAccounts();

        List<UserAccount> removeAccount=userAccountsList.stream().filter(s->s.getId()==userId).collect(Collectors.toList());

        userAccountsList.removeAll(removeAccount);

        storeInfo.setAccounts(userAccountsList);

        //store updated with removal of Account of given Id
        storeService.updateStoreInfo(storeInfo,storeId);

        return ResponseEntity.status(HttpStatus.OK).body(userAccount);


    }

}
