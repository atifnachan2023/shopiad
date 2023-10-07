package com.example.shopad.controller;

import com.example.shopad.model.ProductsInfo;
import com.example.shopad.model.StoreInfo;
import com.example.shopad.model.UserAccount;
import com.example.shopad.services.ProductService;
import com.example.shopad.services.StoreService;
import com.example.shopad.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    StoreService storeService;

    @Autowired
    UserService userService;

    //this api add products in particular store
    @PostMapping("/product/{storeId}")
    public ResponseEntity<StoreInfo> putProductInStore(@RequestBody ProductsInfo productsInfo,@PathVariable String storeId){


        StoreInfo store=storeService.getStore(storeId);

        List<ProductsInfo>existingProductList=store.getProducts();

         ProductsInfo productsInfo1=productService.addProductToStore(productsInfo);


          List<ProductsInfo> newProductList=new ArrayList<>();

          newProductList.addAll(existingProductList);
          newProductList.add(productsInfo1);

          store.setProducts(newProductList);

          storeService.updateProductsInStore(store);

         return  ResponseEntity.status(HttpStatus.CREATED).body(store);


    }

    //This api delete products from store based on storeID
    @DeleteMapping("{productId}/{storeId}" )
    public ResponseEntity<StoreInfo> deleteProductInStore(@PathVariable String productId,@PathVariable String storeId){


        StoreInfo storeInfo=storeService.getStore(storeId);

        List<ProductsInfo>productsList=storeInfo.getProducts();


        if(productsList.stream().anyMatch(a->a.getId().equals(productId))){

            ProductsInfo removeProduct=productService.getProductById(productId);

            productsList.remove(removeProduct);
            storeInfo.setProducts(productsList);
            storeService.updateProductsInStore(storeInfo);
            productService.deleteProductFromStore(productId);

        }

        return ResponseEntity.status(HttpStatus.OK).body(storeInfo);


    }

    // this api updates product in store
    @PutMapping("/{productId}")
    ResponseEntity<ProductsInfo> updateProductInStore(@RequestBody ProductsInfo productsInfo,@PathVariable String productId){


               productsInfo.setCreatedAt(productsInfo.getCreatedAt());

                  ProductsInfo updatedProduct=productService.updateProductInStore(productsInfo,productId);

                   return  ResponseEntity.status(HttpStatus.OK).body(updatedProduct);

    }

}
