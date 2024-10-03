#!/bin/bash

curl_database(){
curl -X 'POST' \
  'http://localhost:4444/api/customers' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 0,
  "personalInfo": {
    "name": "hamit",
    "surname": "mızrak",
    "tcNumber": "tc number",
    "vatNumber": "vat number",
    "notes": "notes",
    "email": "hamitmizrak@gmail.com"
  },
  "address": {
    "id": 0,
    "street": "bostanbaşı",
    "city": "malatya",
    "country": "turkiye",
    "postalCode": "44044"
  },
  "orders": [
    {
      "id": 0,
      "name": "order name",
      "price": "order price",
      "number": "order number",
      "totalAmount": 44
    }
  ]
}'
}

curl_database