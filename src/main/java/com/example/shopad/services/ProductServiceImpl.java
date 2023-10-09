package com.example.shopad.services;

import com.example.shopad.model.ProductsInfo;
import com.example.shopad.model.StoreInfo;
import com.example.shopad.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements   ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;


    @Override
    public ProductsInfo addProductToStore(ProductsInfo productsInfo) {

        String productId = UUID.randomUUID().toString();
         productsInfo.setCreatedAt(Instant.now());
         productsInfo.setUpdatedAt(Instant.now());
        ProductsInfo productsInfo1=new ProductsInfo();

        productsInfo1.setId(productId);

        productsInfo1=productRepository.save(productsInfo);

        return productsInfo1;


    }

    @Override
    public ProductsInfo getProductById(String productId) {

        ProductsInfo productsInfo=productRepository.findById(productId).get();

        return productsInfo;
    }

    @Override
    public void deleteProductFromStore(String productId) {


        productRepository.deleteById(productId);




    }

    @Override
    public ProductsInfo updateProductInStore(ProductsInfo productsInfo, String productId) {

        ProductsInfo productsInfo1=productRepository.findById(productId).get();

        productsInfo1.setCreatedAt(productsInfo1.getCreatedAt());

               if(productsInfo.getProductName()!=null) {
            productsInfo1.setProductName(productsInfo.getProductName());
        }
        if (productsInfo.getCategory()!=null) {
            productsInfo1.setCategory(productsInfo.getCategory());
        }
        if(productsInfo.getPrice()!=null) {
            productsInfo1.setPrice(productsInfo.getPrice());
        }

        if (productsInfo.getIsActive()!=null) {
            productsInfo1.setIsActive(productsInfo.getIsActive());
        }
        productsInfo1.setUpdatedAt(Instant.now());

        productRepository.save(productsInfo1);

        return  productsInfo1;
    }


}
