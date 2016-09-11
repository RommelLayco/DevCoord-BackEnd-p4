========================================================================
DATABASE CONFIGURATION
=========================================================================

The DevCoord plugin is currently using a database hosted by db4.net
The login details of the database are:
	username: 
	password:

To change the database change the url and the database crendentials of the persistence.xml file.
This file is located in \DevCoord-Backend-\tycho\bundles\nz.ac.auckland.devcoord.database\src\META-INF\persistence.xml  

change the following attributes of the xml:






Currently the xml has the following tag <property name="hibernate.hbm2ddl.auto" value="validate"/>. 
When DevCoord run the current database schema is validated. The database schema should be correct. 
Currently the database hosted in db4net contains information.

=======================================================================================================
USING TYCHO TO BUILD DEVCOORD AND TEST
======================================================================================================
To run the test first ensure that database has no information stored. This is because some of the test cases, test the persist method.
This test case will fail if the item that is being persisted already exist in the database.
To empty the database use the SQL commands Drop table [name of all the database tables comma seperated].
If running test repeatly change <property name="hibernate.hbm2ddl.auto" value="validate"/>. to
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


