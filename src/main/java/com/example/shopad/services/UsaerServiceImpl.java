package com.example.shopad.services;


import com.example.shopad.model.UserAccount;
import com.example.shopad.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsaerServiceImpl implements  UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserAccount createUser(UserAccount userAccount) {

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
}
