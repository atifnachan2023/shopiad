package com.example.shopad.services;


import com.example.shopad.model.ProductsInfo;
import com.example.shopad.model.StoreInfo;
import com.example.shopad.model.UserAccount;
import com.example.shopad.repositories.StoreRepository;
import com.example.shopad.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class StoreServiceImpl  implements  StoreService{

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    UserService userService;



    @Override
    public StoreInfo addStore(StoreInfo storeInfo) {
        storeInfo.setCreatedAt(Instant.now());
        storeInfo.setUpdatedAt(Instant.now());

        StoreInfo storeInfo1=storeRepository.save(storeInfo);



        return storeInfo1;
    }

    @Override
    public StoreInfo getStore(String id) {

        String storeId = UUID.randomUUID().toString();

        StoreInfo storeInfo=new StoreInfo();
        storeInfo.setId(storeId);


      storeInfo=storeRepository.findById(id).get();

        return storeInfo;
    }

    @Override
    public List<StoreInfo> getAllUserStores(int id) {

        UserAccount userAccount =userService.getUser(id);
        List<StoreInfo> storeInfo=userAccount.getStores();


        return storeInfo;
    }

    @Override
    public StoreInfo updateStoreInfo(StoreInfo storeInfo,String storeId) {

        List<ProductsInfo>productsInfoList=storeInfo.getProducts();

        StoreInfo store=storeRepository.findById(storeId).get();

        if( storeInfo.getStoreName()!=null ){

            store.setStoreName(storeInfo.getStoreName());

        }
        if ( storeInfo.getDescription()!=null){

            store.setDescription(storeInfo.getDescription());
        }


        if (storeInfo.getIsActive().toString()!=null){

            store.setIsActive(storeInfo.getIsActive());
        }

        if ( storeInfo.getAccounts()!=null){
            store.setAccounts(storeInfo.getAccounts());

        }

        if (storeInfo.getProducts()!=null){

            store.setProducts(storeInfo.getProducts());
        }


        storeRepository.save(store);

        return store;
    }

    @Override
    public StoreInfo updateProductsInStore(StoreInfo storeInfo) {




        StoreInfo storeInfo1=storeRepository.save(storeInfo);

        return storeInfo1;
    }
}
