# ehour-automation

Steps to get the automation up and running-
1. Clone the repository in a folder.
2. Load Eclipse.
3. File >> Import >> Existing Projects to Workspace >> Point to the folder where the project is cloned from git.
4. Modify the config.properties and change the URL on which you want to execute the tests.
5. In the TestRunner.java, change the tags to "~@ignore" Eg. tags={"~@ignore"} 
		

Following are the ways one can run the tests.
a. Right-click pom.xml and run as Maven Test.
b. Right-click runner file and run as JUnit / TestNG
c. Right-click feature file and run as Cucumber Feature (This might not always work)
d. Right-click inside a feature file and run as Cucumber feature.