package com.example.shopad.services;

import com.example.shopad.model.StoreInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreService {

    StoreInfo  addStore(StoreInfo storeInfo);

    StoreInfo getStore(String id);

    List<StoreInfo> getAllUserStores(int userId);


    StoreInfo updateStoreInfo(StoreInfo storeInfo, String storeId);

    StoreInfo updateProductsInStore(StoreInfo storeInfo);


}
