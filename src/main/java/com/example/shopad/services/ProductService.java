package com.example.shopad.services;

import com.example.shopad.model.ProductsInfo;
import com.example.shopad.model.StoreInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {


    ProductsInfo addProductToStore(ProductsInfo productsInfo);

    ProductsInfo getProductById(String productId);

    void deleteProductFromStore(String productId);

    ProductsInfo updateProductInStore(ProductsInfo productsInfo,String productId );

}
