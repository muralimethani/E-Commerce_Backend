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
![List of APIs_page-0001](https://github.com/muralimethani/E-Commerce_Backend/assets/116708943/b00705a2-20d6-4f2d-a649-72462244a3f2)
![List of APIs_page-0002](https://github.com/muralimethani/E-Commerce_Backend/assets/116708943/52292d67-18d5-45b9-8c8a-b7df70cf342d)
![List of APIs_page-0003](https://github.com/muralimethani/E-Commerce_Backend/assets/116708943/198e73cc-9346-466b-9700-275f3c0239e9)



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

	
