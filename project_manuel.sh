#!/bin/bash


# Docker Build
# docker-compose up
# docker-compose up -d

curl_data(){
 curl -X 'POST' \
   'http://localhost:4444/api/customers' \
   -H 'accept: */*' \
   -H 'Content-Type: application/json' \
   -d '{
   "id": 0,
   "personalInfo": {
     "firstName": "hamit",
     "lastName": "mızrak",
     "email": "hamitmizrak@gmail.com",
     "tcNumber": "123456789"
   },
   "address": {
     "id": 0,
     "street": "string",
     "city": "malatya",
     "state": "string",
     "postalCode": "44"
   },
   "orders": [
     {
       "id": 0,
       "orderNumber": "1",
       "totalAmount": 20
     }
   ]
 }'
}
curl_data