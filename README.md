# ParkHub
https://balsamiq.cloud/s6r3imj/p1z7odq/r2278

*************************DATABASE ACCESS INFORMATION***********************
To avoid hardcoding of credentials and access info in "application.variables" for database I have used 3 variables:
DB_ACCESS
DB_USER
DB_PASSWORD
It's environmental variables, which you should update on your local machine according to your local DB.
How to:
We are using Spring Application, so as active configuration should be chosen "BackendApplication".
1.Go to "Edit Configurations";
2."RUN/DEBUG Configurations" window appeared. In a right part of window you need to find roll-out menu "Enviroment";
3.Next, you need to find "Environment variables" field and click on "Browse" button in the end of field.
4.In appeared window you should specify variables DB_ACCESS, DB_USER, DB_PASSWORD by clicking jn a "+"-button 
    in a upper-right corner of window.
5.Click "Ok", then "Apply"

Alex Syrin
*************************DATABASE INIT INFORMATION*************************
First of all, you need to install your own PostgreSQL database server locally or in GCloud SQL to make any changes.
I have added liquibase script to perform initialization of database tables, which runs on a start of application.
But, in testing we found issues with running of DATABASE CREATE part.
So, before you'll start application, please run "DATABASE CREATE <db_name>" manually.

Alex Syrin
With any questions, feel free to contact me in Telegram @Eastern_sun
****************************