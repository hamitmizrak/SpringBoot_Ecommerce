#!/bin/bash



maven_build(){
  mvn -v
  ls -al
  mvn clean
  mvn package -DskipTests
}
maven_build

docker_nginx_build(){
  docker search nginx
  docker pull nginx
  docker container ps
#  docker container run --publish 1111:80 nginx
#docker container run -p 1111:80 --name nginx_container --rm -d nginx
docker container run -p 1111:80 --name nginx_container --rm  nginx
}
#docker_nginx_build

docker_build(){
#  docker-compose up -d
  docker-compose up
}
docker_build

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