========================================================================
DATABASE CONFIGURATION
=========================================================================

The DevCoord plugin is currently using a database hosted by https://www.db4free.net/

The login details of the database are:
	username: devcoordpart4
	password: softeng700

To change the database change the url and the database credentials of the persistence.xml file.

This file is located in \DevCoord-Backend-\tycho\bundles\nz.ac.auckland.devcoord.database\src\META-INF\persistence.xml  

DevCoord currently uses the following credentials.

			<!-- The below three our for database hosted on a test server -->

			<property name="hibernate.connection.url" value="jdbc:mysql://db4free.net:3306/devcoord" />
			<property name="hibernate.connection.username" value="devcoordpart4" />
			<property name="hibernate.connection.password" value="softeng700" />



Change the url, username and password to your chosen database

Currently the xml has the following tag 

			<property name="hibernate.hbm2ddl.auto" value="validate"/>. 

When DevCoord run the current database schema is validated. The keeps the database consistent when the EntityMangerFactory is created

Currently the database hosted in db4net contains information. This information remains in the database, with the validate configuration.

=======================================================================================================
USING TYCHO TO BUILD DEVCOORD AND TEST
======================================================================================================
To run the test first ensure that database has no information stored. This is because some of the test cases, test the persist method.
This test case will fail if the item that is being persisted already exist in the database.

To empty the database use the SQL commands Drop table [name of all the database tables comma seperated].

If running test repeatedly change

<property name="hibernate.hbm2ddl.auto" value="validate"/>. To

TO

<property name="hibernate.hbm2ddl.auto" value="create"/>.

To run test and build project open the command prompt and navigate to the following directory:
\DevCoord-Backend-\tycho\

enter the command mvn clean verify.



==========================================================================================================
RUN PLUGIN FROM ECLIPSE
=======================================================================================================
Ensure that devcoord is connected to a database


Open the tycho project. 


Run as Eclipse application

DevCoord should be running

to test create task from a remote repository
This is because a remote repositiory is able to create additional information such as the OS, Platform  and Component Attributes
The task created needed these attributes as the database rejects null values for theses attributes.

================================================================================================
CONNECT TO REMOTE TASK REPO - BUGZILLA
==============================================================================================


