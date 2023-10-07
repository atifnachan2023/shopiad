this project is developed on

(1)MongoDb
(2)Spring Boot version 2.7.16
(3)Spring security
(4)Java 8


Following End Point are created

To create User/Seller Account (POST call)

http://localhost:8081/account/user

To Fetch User/Seller Account (GET CALL)

http://localhost:8081/account/{userId}

To Assign store to particular User/Seller

http://localhost:8081/account/store/{storeId}/{userId}

To create a store
http://localhost:8081/stores/store/{storeId} (POST CALL)

To Update particular store (UPDATE CALL)

http://localhost:8081/stores/{storeId}

To create a product in store (POST CALL)

http://localhost:8081/products/product/{storeId}


To delete product in Store (DELETE CALL)

http://localhost:8081/products/{productId}/{storeId}


To update product in store(UPDATE CALL)

http://localhost:8081/products/{productId}



