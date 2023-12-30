#!/bin/bash


read -p "username mysql:" username
read -s -p "password mysql:" password

# application.properties
sed -i "s/username=.*$/username=$username/" src/main/resources/application.properties
sed -i "s/password=.*$/password=$password/" src/main/resources/application.properties

# persistence.xml
sed -i "s/user\" value=\".*\"/user\" value=\"$username\"/" src/main/resources/META-INF/persistence.xml
sed -i "s/password\" value=\".*\"/password\" value=\"$password\"/" src/main/resources/META-INF/persistence.xml
mvn spring-boot:run
