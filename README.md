# rmm-services-server-app

1- To create database execute script "rmm.sql"
2- Change line 6 in "application.properties" file, found in the "resource" folder of the application, to this "spring.datasource.initialization-mode=always"
With this change the first time that the application run will set some data to the database using the file "data.sql" found in the "resource" folder of the application
3- Import Existing Maven Project with Eclipse
4- Open a project terminal in eclipse and run "mvn clean install" command.
5- Run the application (right click over the application - Run As - Java Application).
Select "RmmServiceServerAppApplicataion" and click "Ok"
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
