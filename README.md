##### APIS

 

###### ENTITY SET UP
http://localhost:9096/entity/create/
Content-Type: application/json
authorization: Basic dXNlcjpwYXNzd29yZA==
METHOD:POST
BODY
	{  
	  "id":null,
	  "name":{
			"first":"Vivek",
			"middle":"Kumar",
			"last":"Agrawal"
	  },
	  "address":{
			"addressLine1":"address1",
			"addressLine2":"address2",
			"country":"India",
			"postalAddress":"504293",
			"state":"ANDHRA PRADESH",
			"city":"Adilabad"
	  },
	  "email":"vivek081141@gmail.com",
	  "mobile":"8951152580",
	  "status":"ACTIVE"
	}  


###### ENTITY LIST
http://localhost:9096/entity/entities
METHOD TYPE: GET

###### ENTITY ID
http://localhost:9096/entity/1
METHOD TYPE: GET

###### ENTITY DEACTIVATE 
METHOD TYPE: PUT
http://localhost:9096/entity/deactivate?entityId=1


###### CUSTOMER SET UP
http://localhost:9096/customer/create/
Content-Type: application/json
authorization: Basic dXNlcjpwYXNzd29yZA==
METHOD:POST
BODY
	{  
	  "id":null,
	  "name":{
			"first":"Vivek",
			"middle":"Kumar",
			"last":"Agrawal"
	  },
	  "address":{
			"addressLine1":"address1",
			"addressLine2":"address2",
			"country":"India",
			"postalAddress":"504293",
			"state":"ANDHRA PRADESH",
			"city":"Adilabad"
	  },
	  "email":"vivek081141@gmail.com",
	  "mobile":"8951152580",
	  "status":"ACTIVE",
	  "entityId":"1"
	}  


###### CUSTOMER LIST
http://localhost:9096/customer/customers
METHOD TYPE: GET

###### CUSTOMER ID
http://localhost:9096/customer/1
METHOD TYPE: GET

###### CUSTOMER DEACTIVATE 
METHOD TYPE: PUT
http://localhost:9096/customer/deactivate?entityId=1

###### ORDER CREATE
http://localhost:9097/customer/1/order/create
Content-Type: application/json
authorization: Basic dXNlcjpwYXNzd29yZA==
METHOD TYPE:POST
	{  
	  "id":null,
	  "customerId":1,
	  "name":{
			"first":"Vivek",
			"middle":"Kumar",
			"last":"Agrawal"
	  },
	  "addressA":{
			"addressLine1":"address1",
			"addressLine2":"address2",
			"country":"India",
			"postalAddress":"504293",
			"state":"ANDHRA PRADESH",
			"city":"Adilabad"
	  },
	  "addressB":{
			"addressLine1":"address1",
			"addressLine2":"address2",
			"country":"India",
			"postalAddress":"504293",
			"state":"ANDHRA PRADESH",
			"city":"Adilabad"
	  
	  },
	  "isPickUp":false,
	  "quotedPrice":200,
	  "quantity":100,
	  "weight":12,
	  "comments":"Putting Comments",
	  "productDescription":"Description",
	  "status":"DRAFT",
	  "offeredPrice":0
	}

######## Order Modify
http://localhost:9097/customer/1/order/update
METHOD TYPE: PUT
	{  
	  "id":1,
	  "customerId":1,
	  "name":{
			"first":"Vivek",
			"middle":"Kumar",
			"last":"Agrawal"
	  },
	  "addressA":{
			"addressLine1":"address1",
			"addressLine2":"address2",
			"country":"India",
			"postalAddress":"504293",
			"state":"ANDHRA PRADESH",
			"city":"Adilabad"
	  },
	  "addressB":{
			"addressLine1":"address1",
			"addressLine2":"address2",
			"country":"India",
			"postalAddress":"504293",
			"state":"ANDHRA PRADESH",
			"city":"Adilabad"
	  
	  },
	  "isPickUp":false,
	  "quotedPrice":200,
	  "quantity":100,
	  "weight":12,
	  "comments":"Putting Comments",
	  "productDescription":"Description",
	  "status":"APPROVED",
	  "offeredPrice":10000
	}   


				 