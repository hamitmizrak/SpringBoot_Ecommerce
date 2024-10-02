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
    "firstName": "string",
    "lastName": "string",
    "address": {
      "id": 0,
      "street": "string",
      "city": "string",
      "state": "string",
      "postalCode": "string"
    },
    "orders": [
      {
        "id": 0,
        "orderNumber": "string",
        "totalAmount": 0
      }
    ]
  }'
}
curl_data