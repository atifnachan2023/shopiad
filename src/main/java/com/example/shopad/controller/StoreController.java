package com.example.shopad.controller;


import com.example.shopad.model.StoreInfo;
import com.example.shopad.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

    @Autowired
    StoreService storeService;


    // this Api creates a new Store
    @PostMapping("/store")
    public ResponseEntity<StoreInfo> createStore(@RequestBody StoreInfo storeInfo){

        StoreInfo store=storeService.addStore(storeInfo);

        return ResponseEntity.status(HttpStatus.CREATED).body(store);

    }

    //this api fetches store based on id
    @GetMapping("/store/{id}")
    public ResponseEntity<StoreInfo> getStore(@PathVariable String id){

        StoreInfo store=storeService.getStore(id);

        return ResponseEntity.status(HttpStatus.OK).body(store);

    }

    //this Api update store info
    @PutMapping("/{storeId}")
    public ResponseEntity<StoreInfo> updateStore(@RequestBody StoreInfo storeInfo,@PathVariable String storeId){




        StoreInfo storeInfo1=storeService.updateStoreInfo(storeInfo,storeId);

        return ResponseEntity.status(HttpStatus.OK).body(storeInfo1);



    }


    /*public  ResponseEntity<List<StoreInfo>> getAllUserStores(){

        storeService.


    }*/

}
