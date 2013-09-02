## Realtime UI Notification

Project to simulate real time UI notifications. There could be large backend jobs (services) running on <br>
which need to notify status of job to user on realtime. 

## Development Requirement

* This project is mainly based on following technologies with latest version on (09/02/2013)<br>
* Web server to deploy and execute application: [Apache Tomcat - 7](http://tomcat.apache.org/)
* Messaging and Integration patterns server: [Active MQ 5.8.0](http://activemq.apache.org/)
* JSON for message communication: [Jakson Library](http://jackson.codehaus.org/)
* Code compiled and tested on JSDK - 7
* On client side it uses HTML-5, websocket plugin on HTML-5 and Java script

## Building and Testing

To build the code:

  <pre><code> ant buildAndDeploy </code></pre>

To run server application:

   <pre><code> ant runServer </code></pre>
   
## Use and TestCases

* As this application is using websocket plugin of HTML - 5 so it will only work on browser which supports <br>
  HTML-5 websocket plugin (e.g. Latest verion of Mozilla firefox, Google Chrome)
* Database : This project uses [PostgreSQL](www.postgresql.org/). Use following commnds to create required <br>
  database and tables
  
  <pre><code>
  CREATE DATABASE web_notification  WITH OWNER = postgres  ENCODING = 'UTF8' TABLESPACE = pg_default
  
  CREATE TABLE users
  (
    id serial NOT NULL,
    user_name character varying(255) NOT NULL,
    "password" character varying(255) NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
  )
  
  INSERT INTO users VALUES(1,'test1','test123')
  INSERT INTO users VALUES(2,'test2','test123')
  INSERT INTO users VALUES(3,'test3','test123')
  </code></pre>
* Start ActiveMQ with command activemq of bind directory of ActiveMQ installation.
* This application contains job (NotificationProducer) located at `com.collective.job` for producing runtime<br>
  notifications. We have to execute this java class. After every minute it will produce notification for any<br>
  one of userid(1,2,3) and send it to queue. Also after every 1.5 minute it will produce global notification which will be broadcast to every connected user.
