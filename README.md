# ECommerce Backend Application


 

## Techonology Used

- ###### Java

- ###### Spring Boot

- ###### Hibernate

- ###### RESTful APIS

- ###### Maven

- ###### My SQL

- ###### Postman




## EER Diagram
![Schema](https://user-images.githubusercontent.com/116377954/222906823-f7682629-2383-496b-91a0-923bcedd9b00.png)

## List of API's
[List of APIs.pdf](https://github.com/muralimethani/E-Commerce_Backend/files/13742322/List.of.APIs.pdf)





## Functionalities

###### Seller

	• Add Seller

	• Update Seller By Email Id
       
		
###### Product

	• Get Available Products

	• Add Product


###### Customer

	• Get By Email Id

	• Update By Email Id

    

  
###### Cart

	• CheckOut Cart

	• Delete Cart By Id
      
   ###### Order

	• Place Order Directly

	• Get Recent 5 Orders

## Postman Commands

###### To Add Seller:

	localhost:8080/seller/add

	{
    "name" : "Panga",
    "age" : 22,
    "emailId" : "iamPanga@gmail.com",
    "mobNo" : "8367400578"
     }



###### Update Seller By Email Id:
	
	localhost:8080/seller/update_byEmailId?emailId=muralirgukt10@gmail.com

	{
    "name" : "Vijay Murali",
    "age" : 24
}
	

###### Get Available Products:

	localhost:8080/product/get/SPORTS

	



###### Add Product:
	
	localhost:8080/product/add

	{
    "productName" : "IPhone 15 ProMax",
    "price" : 100000,
    "quantity" :100,
    "sellerId" :1,
    "productCategory" : "ELECTRONICS"
}

	
###### Get Customer By emailId:

	localhost:8080/customer/get/byEmailId?emailId=ranbir@animal.com
  
 


###### Update By Email Id:	

	localhost:8080/customer/updateByEmailId?emailId=iamIronMan@com
  
  	{
    	"mobile" : "9999999999",
    	"age" : 53
	}



###### CheckOut Cart:	

	localhost:8080/cart/checkout
  
	{
    	"customerId":1,
    	"cardNumber":2233445566,
    	"cvv":9876
	}



###### Delete Cart By Id:	

	localhost:8080/cart/deleteCart?id=1
  



###### Place Order Directly:	

	localhost:8080/order/place
	{
    	"customerId":1,
    	"productId":1,
    	"requiredQuantity":1,
    	"cardNo":2233445566,
    	"cvv":9876
	}


###### Get Recent 5 Orders:	

	localhost:8080/order/recent5orders?customerId=1
  



## Future Scope

1. Multiple customer handling 
2. Product locking during payment
3. Multiple Orders handling 
4. Payment Flow
5. Login and User Account Management
6. Authentication and Authorization

	
