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

To empty the database use the following SQL commands.

		drop table ACTUAL_SCORES, CONTEXT_STRUCTURES, POTENTIAL_SCORES, hibernate_sequence, TASKS, TASK_PAIRS

If running test repeatedly and have the tables drop automatically change

		<property name="hibernate.hbm2ddl.auto" value="validate"/>.

TO

		<property name="hibernate.hbm2ddl.auto" value="create"/>.

To run test and build project open the command prompt and navigate to the following directory:
		
		\DevCoord-Backend-\tycho\

enter the command 
		
		mvn clean verify.


		
All the test should pass, the build must be sucessful

========================================================================================================
IMPORT PROJECT INTO ECLIPSE
======================================================================================================
Import the devcoord project into eclipse by:

Navigate to file > import.

Select existing projects into workspace

Select the root directory to be:

	LocationOfTheDevCoordProject\DevCoord-Backend-\tycho

import the project nz.ac.auckland.devcoord. This project should be ticked by eclipse.

In the project explorer, change project presentation to heirarchical

In eclipse open the bundles folder. Import the following folders as project.
(Right click folder, select import as project)

	nz.ac.auckland.devcoord.database
	nz.ac.auckland.devcoord.hibernate2
	nz.ac.auckland.devcoord.machineLearning
	nz.ac.auckland.devcoord.plugin
	
In eclipse open the releng folder. Import the following folders as project.
	
	nz.ac.auckland.devcoord.configuration
	
In eclipse open the tests folder. Import the following folders as project.

	nz.ac.auckland.devcoord.databaseTest
	nz.ac.auckland.devcoord.machineLearningTest
	


==========================================================================================================
RUN PLUGIN FROM ECLIPSE
=======================================================================================================
Ensure that devcoord is connected to a database


Open the tycho project in eclipse.. 


Run as Eclipse application, by clicking the green play button. 
If run as a new eclipe application is not shown in the play button follow these steps.

	
	Click the down arrow next to the play button and select Run Configurations.

	On the menu on the left of the window that pops up select eclipe application.
	
	Create a new configuration of the type you have selected (press the new button). The press run.
	

Wait for a new eclipse application to load.

To view the DevCoord GUI goto

	window > show view > other > DevCoord2
	


Create a dummyh project and tasks.
To test create task from a remote repository
This is because a remote repositiory is able to create additional information such as the OS, Platform  and Component Attributes
The task created needed these attributes as the database rejects null values for theses attributes.

================================================================================================
CONNECT TO REMOTE TASK REPO - BUGZILLA
==============================================================================================

Bugzilla can be installed on a server using the instructions stated here-
[The Bugzilla Guide](https://www.bugzilla.org/docs/2.18/html/installation.html).


For an easier alternative, setup an account on an internet service that provides a free/paid bugzilla repo to play with,such as [Devzing](http://devzing.com/).

once the devzing account is setup,take note of the following-
* URL of the bugizlla repositoy
* USERNAME of the administrator of the bugizlla repositoy
* PASSWORD of the administrator of the bugzilla repository

The above information is provided by Devzing thru automated email,upon account activation.
Logging in thru browser,using the information above,provides a view of the repository for the admin, thru which bugs and additional users can be created.

To connect the repository to Mylyn plugin-

1. Click Run->Run As->Eclipse Application
2. Once the application is running
3. Click Window->Show view->Other->Task Repositories
4. Right click,then click Add Task Repository
5. Click on Bugzilla,then on next
6. In the 'Server' field,write the URL of the bugzilla repository
7. For the 'Label' field, chose an appropriate name
8. In the 'User ID' field,write the USERNAME of the bugzilla repository(or user name of any other user created via browser)
9. In the 'Password' field,write the PASSWORD of the bugzilla repository(or password of any other user created via browser)
10. Check 'Save Password'
11. Click Finish

The above steps will synchronyze the Mylyn view with the remote Bugzilla repository.
Mylyn can then be used to control the repository(e.g. Add/Delete tasks etc.)
