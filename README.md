# _Hair Salon Client/Stylist Tracker_

#### _A web application for tracking which clients see which stylists at a hair salon, May 6, 2016_

#### By _**Nick Lyman**_

## Description

_This web app uses Java and a SQL database for storing and keeping track of the client list for each stylist. It is currently set up for allowing a client to see only one stylist for a one to many relationship. A user is able to add, update, or remove the name of one or more stylist and/or the name of one or more clients, as well as assign which stylist the client will visit._

## Setup/Installation Requirements

* _Link to repository: https://github.com/nicklyman/0506_java-salon.git_
* _Clone this repository to your Github account_
* _Create a project directory up on your computer_
* _Have Java installed on your computer_
* _Have Gradle installed on your computer_
* _Use your command line to access the cloned repository on your computer_
* _Have Postgres SQL installed on your computer_
* _Use the command line to start Postgres SQL by typing "psql" without quotes into the command prompt and create a database as follows: CREATE DATABASE hair_salon_
* _Next, create your stylist database table as follows: CREATE TABLE stylists (id serial PRIMARY KEY, stylist_name varchar)_
* _Next, create your client database table as follows: CREATE TABLE clientes (id serial PRIMARY KEY, client_name varchar, stylist_id int)_
* _Now the two tables are linked together using the PRIMARY KEY of the stylist with the stylist_id in the clients table_
* _For creating a testing database, type: CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon_
* _In a seperate tab in the command prompt, type "gradle run" without quotation marks in the command line and it should build the application_
* _Open a web browser window and type "localhost:4567" without quotation marks and it should open the game in your browser window_
* _Now it is possible to add, update, and remove stylists and clients from the database_

_Database details listed below:_
* _CREATE DATABASE hair_salon_
* _CREATE TABLE stylists (id serial PRIMARY KEY, stylist_name varchar)_
* _CREATE TABLE clients (id serial PRIMARY KEY, client_name varchar, stylist_id int)_
* _CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon_

## Known Bugs

_Not currently able to update or delete clients and some links not working properly_

## Support and contact details

_If a bug is found, please let me know via Github. Feel free to contact me with questions or suggestions and contribute to the code._

## Technologies Used

* _Git_
* _Github_
* _Atom text editor_
* _Java_
* _Spark_
* _VelocityTemplateEngine
* _Bootstrap_
* _Postgres SQL_

### License

*This software is licensed under the MIT license*

Copyright (c) 2016 **_Nick Lyman_**
