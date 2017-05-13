# blackboardServer
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

