# rmm-services-server-app

1- Import Existing Maven Project with Eclipse.
2- Open a project terminal in eclipse and run command "mvn clean install".
3- Change line 6 in "application.properties" file, found in the "resource" folder of the application, to this "spring.datasource.initialization-mode=always"
5- Run the application (right click over the application - Run As - Java Application).
Select "RmmServiceServerAppApplicataion" and click "Ok".
With the step 3 and after step 5 will create the database "rmm" and will set some data to the tables using the file "data.sql" found in the "resource" folder of the application
6- Change line 6 in "application.properties" file, found in the "resource" folder of the application, to this "spring.datasource.initialization-mode=never"
Step 6 will prevent the "data.sql" file from being executed every time the application is run.
-----------------------------------------------------------------------------------------------
Postman: Basic Auth
Username: user
Password: password

Device CRUD
GET - localhost:8080/device - Get all devices
POST - localhost:8080/device - Create a device
{
    "system": "android",
    "types": {
        "id": 3,
        "name": "Mac"
    }
}
PUT - localhost:8080/device/3 - Update a device
{
    "id":3,
    "system": "IOs",
    "types": {
        "id": 3,
        "name": "Mac"
    }
}
DELETE - localhost:8080/device/3 - Delete a device
------------------------------------------------------------------------------------------------
Customer Services
POST - localhost:8080/customerService - Add available services to his/her account
{
"customer": {
    "id": 1,
    "name": "Osvaldo"
},
"device": {
    "id": 3,
    "system": "OIs",
    "types": {
        "id": 3,
        "name": "Mac"
    }
},
"service": {
    "id": 4,
    "name": "TeamViewer"
}
}
GET - localhost:8080/customerService/serviceByCustomer/1 - Get all services selected by customer
DELETE - localhost:8080/customerService/1/2/3 - Delete available services fro customer account
GET - localhost:8080/customerService/calculateTotalMonthy/1/2022-01-01/2022-01-31 - Calculate total monthly cost
