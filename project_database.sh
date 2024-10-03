#!/bin/bash

curl_database(){
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
      "street": "bostanbaşı",
      "city": "malatya",
      "state": "state",
      "postalCode": "44044"
    },
    "orders": [
      {
        "id": 0,
        "orderNumber": "hxz123",
        "totalAmount": 44
      }
    ]
  }'
}

curl_database