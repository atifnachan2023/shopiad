package com.example.shopad.services;

import com.example.shopad.model.UserAccount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

   UserAccount createUser(UserAccount userAccount);

   List<UserAccount> getUsers();

   UserAccount getUser(int id);

}
