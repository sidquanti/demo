# start

This is a Spring boot web app written in Kotlin (Java 8)

DB used : Mysql 8

To run this project on local : 

0. Install Java 8 and Mysql 8 , Intellij IDE
1. Download the project to your machine
2. Start your mysql, command : mysql.server start
3. In DB create a schema name demo, command : create SCHEMA demo
4. Execute create table mysql statement from file : initial-tables.sql
5. Change to your db username and password in code file : application.yml
6. Run main method in DemoApplication.kt file
7. hit http://localhost:8080/library from your browser

# end
