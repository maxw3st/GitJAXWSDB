# Git-JAX-WS-DB
Server side JAX-WS with MySQL database Client apps have been added. The code shown is only that contained in the NetBeans project source folders. The entire application includes a server-side application project folder ("SvcDemo"), and a separate client project folder ("SvcClient"). 

DB-setup-instructions:

Use the "userSetup_mysql.txt" to update your database with a username and password. The two lines in the file should be used as command line instructions while you are connected to the database. Feel free to use whatever values you like for the user name and password.

About the app.

This application was developed in NetBeans as a JAX web service app using the Maven framework (Java EE 7, NB 8, JDK 1.0.8_25+). It is designed to work with a MySQL database and separate client application (which accesses the web service remotely through WSDL files). The repo contains a console based client (with menu), and an interactive GUI client. The GUI version contains all the functionality of the console based client. The GUI client can be configured for remote distribution.
