##### APIS

###### CUSTOMER SET UP 

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





				 