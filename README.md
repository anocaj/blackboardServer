# blackboardServer

##REST API

| Methode   | Beschreibung | URI  | Body | Rückgabe  |
| ------------- | ------------- | ------------- | ------------- | -------------  |
| GET  | Zeige alle Blackboards  | blackboards/show_blackboards   | leer  | JSON Liste mit allen Blackboards als Objekte   |
| Content Cell  | Content Cell  | Content Cell  | Content Cell  | leer  |


Blackboards Management with mysql and rest

https://spring.io/guides/gs/accessing-data-mysql/

##Import as Maven Project
Build project

##MySQL

Install MySQL if necessary

Start MySQL
```terminal
sudo mysql --password
```


```sql
mysql> create database blackboards; -- Create the new database
mysql> create user 'testuser'@'localhost' identified by 'ThePassword'; -- Creates the user
mysql> grant all on blackboards.* to 'testuser'@'localhost'; -- Gives all the privileges to the new user on the newly created database

```


## Run Spring Application

./mvnw spring-boot:run

## Build Jar

./mvnw clean package
