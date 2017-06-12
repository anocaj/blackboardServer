# blackboardServer

##REST API

| Methode   | Beschreibung | URI  | Body | Rückgabe  |
| ------------- | ------------- | ------------- | ------------- | -------------  |
| GET | Zeige alle Blackboards  | blackboards/show_blackboards | leer | JSON Liste mit allen Blackboards als Objekte |
| GET | Überprüfe ob Blackboard existiert | blackboards/exists_blackboard/{blackboard_name} | leer | Nachricht ob Blackboard existiert oder nicht |
| POST | Erstelle ein Blackboard | blackboards/create_blackboard | name(String) | Nachricht ob Blackboard erstellt wurde oder nicht  |
| PUT | Füge dem Blackboard eine Nachricht hinzu | blackboards/display_blackboard/{blackboard_name} | message(String) | Nachricht ob Änderung erfolgreich gespeichert wurde |
| GET | Zeige bestimmtes Blackboard an | blackboards/read_blackboard/{blackboard_name} | leer | Nachricht über den Inhalt einer Nachricht |
| PUT | Lösche Nachricht eines bestimmten Blackboards | blackboards/clear_blackboard/{blackboard_name} | leer | Meldung über den Erfolg der Löschung der Nachricht |
| DELETE | Lösche ein bestimmtes Blackboard |blackboards/delete_blackboard/{blackboard_name} | leer | Meldung über den Erfolg der Löschung des Blackboards | 
| GET | Überprüfe ob ein Blackboard eine Nachricht enthält | blackboards/blackboard_status/{blackboard_name} | leer | Nachricht ob das Blackboard eine Nachricht enthält oder nicht |
 


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
