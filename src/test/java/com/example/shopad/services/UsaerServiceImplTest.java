package com.example.shopad.services;

import com.example.shopad.model.UserAccount;
import com.example.shopad.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsaerServiceImplTest {


    @MockBean
    UserRepository userRepository;

    @Autowired
    UsaerServiceImpl usaerService;


    @Test
    public void addUserTest(){

        UserAccount userAccount=new UserAccount(123,"Abdullah","abd","abdulla","abd@gmail.com","abd2010", Instant.now(),Instant.now(),true,null);


        Mockito.when(userRepository.save(userAccount)).thenReturn(userAccount);

        assertEquals(userAccount,usaerService.createUser(userAccount));

    }

    @Test
    public void getUsersTest(){

        Mockito.when(userRepository.findAll()).thenReturn(Stream.of(new UserAccount(123,"Abdullah","abd","abdulla","abd@gmail.com","abd2010", Instant.now(),Instant.now(),true,null)
,new UserAccount(558,"Atif","abvv","ati","abd@gmail.com","ati", Instant.now(),Instant.now(),true,null)).collect(Collectors.toList()));

        assertEquals(2,usaerService.getUsers().size());

    }

    @Test
    public void getUserTest(){

        UserAccount userAccount=new UserAccount(123,"Abdullah","abd","abdulla","abd@gmail.com","abd2010", Instant.now(),Instant.now(),true,null);

        Mockito.when(userRepository.findById(userAccount.getId())).thenReturn(Optional.of(userAccount));

        assertEquals(userAccount,usaerService.getUser(userAccount.getId()));


    }



}