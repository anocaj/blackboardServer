# blackboardServer
Blackboards Management with MySQL und REST   
:ship-it:
## REST API

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
 

## Import as Maven Project
Build project

## MySQL

1. Installiere MySQL wenn nötig.
2. Start MySQL
    ```terminal
    sudo mysql --password
    ```
3. Datenbank "blackboards" erstellen. Datenbankbenutzer mit Passwort erstellen. Alle benötigten Zugriffsrollen an den zuvor erstellten Datenbankbenutzer zuweisen. 
    ```sql
    mysql> create database blackboards; -- Create the new database
    mysql> create user 'testuser'@'localhost' identified by 'ThePassword'; -- Creates the user
    mysql> grant all on blackboards.* to 'testuser'@'localhost'; -- Gives all the privileges to the new user on the newly created database
    ```
4. Anschließend im importierten Java Programm unter dem Verzeichnis Resources die Datei application.properties anpassen. Je nachdem ob das ganze lokal oder auf heroku deployed wird muss diese Datei verändert werden.
   
   Für eine lokale mySQL Instanz: 
   ```
   spring.jpa.hibernate.ddl-auto=create 
   spring.datasource.url=jdbc:mysql://localhost:3306/blackboards 
   spring.datasource.username=testuser 
   spring.datasource.password=ThePassword 
   ```
5. Für die Heroku Instanz muss die Datei wie folgt aussehen: 
    ```
    spring.jpa.hibernate.ddl-auto=update 
    spring.datasource.url=jdbc:mysql://b66ecf66494e2f:74305f5a2bd87b9@eu-cdbr-west-01.cleardb.com/heroku_7c384ffbbbc02ac?reconnect=true 
    spring.datasource.username=b66ecf66494e2f 
    spring.datasource.password=74305f5a2bd87b9 
    ```
    Sobalt Spring die benötigten Datenbanktabellen erstellt hat, kann von create auf update gestellt werden 
## Run Spring Application
Als nächstes muss der Server als Spring Boot Application gestartet werden. Sollten sie Intellij verwenden so kann das ganze einfach über die IDE getestet werden. Über die Konsole kann mit folgendem Befehl die Konsole gestartet werden. 
    
    ```
    ./mvnw spring-boot:run
    ```
## Build JAR File
Um den Vorgang zu erleichtern kann einfach mittels Maven eine JAR Datei erstellt werden. 
    
    ```
    ./mvnw clean package
    ``
    
