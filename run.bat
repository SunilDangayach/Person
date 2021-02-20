:install 
mvn clean install

:run
mvn clean package; java -jar target/person-1.0-exe.jar

:test
mvn clean test
