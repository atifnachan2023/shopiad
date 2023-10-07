package com.example.shopad.services;


import com.example.shopad.model.StoreInfo;
import com.example.shopad.model.UserAccount;
import com.example.shopad.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsaerServiceImpl implements  UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserAccount createUser(UserAccount userAccount) {

        userAccount.setCreatedAt(Instant.now());
        userAccount.setUpdatedAt(Instant.now());
        userRepository.save(userAccount);

        return userAccount;
    }

    @Override
    public List<UserAccount> getUsers() {

        return userRepository.findAll() ;
    }

    @Override
    public UserAccount getUser(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public UserAccount updateUser(UserAccount userAccount, int userId) {

        UserAccount userAccount1=userRepository.findById(userId).get();

        userAccount1.setFirstName(userAccount.getFirstName());
        userAccount1.setLastName(userAccount.getLastName());
        userAccount1.setUserName(userAccount.getUserName());
        userAccount1.setPassword(userAccount.getPassword());
        userAccount1.setStores(userAccount.getStores());


        return userRepository.save(userAccount1);
    }

    @Override
    public List<StoreInfo> getStoresForParticularUser(int id) {

        UserAccount userAccount=userRepository.findById(id).get();

        List<StoreInfo>stores=userAccount.getStores();


        return stores;
    }


}
