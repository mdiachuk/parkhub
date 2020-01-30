
  
    
# ParkHub 
**What is ParkHub?**<br>    
  ParkHub is a flexible application for a drivers and for parking-business owners.    
      
  **System Requirments**<br>    
  Application built using JDK 1.8<br>    
  At least 2Gb RAM<br>    
  2,5Gb of free disk space<br>    
      
  **Running Application**<br>    
Our application running on Google App Engine and stores database on Google Cloud SQL with PostgreSQL.

And it's comfortable to configure, build and run in IDE as IntelliJ IDEA.  
      
**DATABASE CONFIGURATION** <br>    
First, configure Google Cloud SQL: <br>    
  
 - Create Instance in console with PostgreSQL 11 in "europe-west3" region. Set up password for default user and name instance. It could take a while.  
 - After instance was created, note IP-address of it.   
 - Create database in instance and note its name.
 - In a "Connection" section of active instance, you need to add your external IP address into whitelist. It allows connections from you network to instance.
    
To avoid hardcoding of credentials and access info in "application.variables" for database we have used 3 environmental variables:      
<b>DB_ACCESS      
DB_USER      
DB_PASSWORD </b>    
It's environmental variables in IDE, which you should update on your local project according to your local DB.      
How to:      
We are using Spring Application, so as active configuration should be chosen "BackendApplication".      
    
 - Go to "Edit Configurations";    
 - "RUN/DEBUG Configurations" window appeared. In a right part of window you need to find roll-out menu "Enviroment";     
 - Next, you need to find "Environment variables" field and click on "Browse" button in the end of field.      
 - In appeared window you should specify variables DB_ACCESS, DB_USER, DB_PASSWORD by clicking jn a "+"-button in a upper-right corner of window.      
 - Click "Ok", then "Apply".  
<br>

<b>DB_ACCESS</b> field contains an external link to existing database, looks like this:<br>
	jdbc:postgresql://<b>IP_ADDRESS_OF_INSTANCE</b>:5432/<b>DATABASE_NAME</b>?usessl=false<br>
You need to replace fields with IP and DB name of your instance:
 - IP_ADDRESS_OF_INSTANCE
 - DATABASE_NAME

After it, run "BackendApplication".
For a first start it will initialise tables and primary data in tables to make database ready to use.
Our application uses Liquibase version control system for database. Application contains all needed scripts to initiate and fill DB.<br>
With needed changes you should to create Changeset and run application and changes will apply to DB.<br>
<b>Performing any changes to existing changesets and scripts may cause DB failure.</b>
   
**SMTP configuration**<br>  
application.properties file has section with configuration of mail-server. <br>
Our application uses GMAIL to send e-mails. But it's possible to configure your own SMTP-server:

 - spring.mail.host=smtp.gmail.com (address of server)
 - spring.mail.port=587 (default port of SMTP)
 - spring.mail.username= (required address)
 - spring.mail.password= (required password)
 - spring.mail.properties.mail.smtp.auth=true
 - spring.mail.properties.mail.smtp.starttls.enable=true
 
 **Running application on APP Engine**<br>
 
There are two Spring profiles configured in Maven project for different purposes: develop and release with specific environmental variables in application-develop.properties and application-release.properties.

For using develop profile (that is used by default) application deploys and runs on localhost:8080(backend) with embedded Tomcat servlet container and jar packaging and localhost:4200(frontend) with Node.js server using webpack build tool and proxy config from localhost:8080. For running backend application you can:

Use command line with a next command:
for Windows environment:<br>
<b> 
    $Env:DB_ACCESS = "YOUR_CREDENTIALS"<br>
    $Env:DB_USER = "YOUR_CREDENTIALS"<br>
    $Env:DB_PASSWORD = "YOUR_CREDENTIALS"<br>
    mvn spring-boot:run<br></b>
    
for Linux environment:<br>
<b>
    export DB_ACCESS="YOUR_CREDENTIALS"<br>
    export DB_USER="YOUR_CREDENTIALS"<br>
    export DB_PASSWORD="YOUR_CREDENTIALS"<br></b>

or press Run button of your IDE

For using release profile please run <b>mvn clean package -P release</b> command for 
packaging project into war(as embedded Tomcat was excluded from configuration) and 
<b>mvn appengine:deploy -P release</b> for deploy application into GCP App Engine. 
Please note that there is no need to deploy frontend additionally as it configured in a way of becoming a static content as you can see in target folder after packaging.

App Engine configuration: for having possibility to pass sensitive data(such as DB name, password, project ID etc) and configure different profiles for different purposes (like CI, App Engine etc) Park Hub project has config.sh script for generating properties file from appengine-web.xml. This file consist sensible data and configured to .gitignore not to be spread in a network and to be stored locally only. Script config.sh translates data from appengine-web.xml to environment variables in appengine-web.xml.template before deploy phase on App Engine.
 
 **Authors**
 
  - Valeriia Salivon
  - Olena Romanenko
  - Maksym Diachuk
  - Yaroslav Podzirei
  - Inna Kamenska
  - Anna Lubska
  - Oleksii Syrin
  - Oksana Murdza
  - Oles Bovsunovskyi