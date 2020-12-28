1. Create the database by running the following SQL commands in MySQL:

create database adminwildfly8;
			
use adminwildfly8;

create table property (
id VARCHAR(100),
value VARCHAR(100),
PRIMARY KEY (id)
);
		
2. Install the MySQL module into WildFly. This can be copied from project 'chapter4' root folder, under the directory 'wildfly'. 
	
3. Add the correct configuration to your wildfly server:

4. Start server

5. Build the application; cd into project root. Run 'mvn clean install'. 

6. Deploy the built artifact to WildFly by either CLI or the admin console. Artifact can be found under 'target' folder.

NOTE:			
Make sure that jboss-client.jar is added to classpath before trying run the main method in the RemoteEJBClient.java. This jar can be found in the /lib folder of the eclipse project.
