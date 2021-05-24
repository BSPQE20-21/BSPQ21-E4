# BSPQ21-E4

[GitHub Pages](https://bspqe20-21.github.io/BSPQ21-E4/)


## Needed commands to execute the project:

1. Remove previous binary files and compile the project:

 	mvn clean compile


2. Open MySQLWorkbench and connect to the database

	run database.sql

3. Create shema

	cmd: mvn datanucleus:schema-create

3. Run the Web Server:

 	cmd: mvn exec:java -Dexec.args="--server"
 	PowerShell: mvn exec:java "-Dexec.args='--server'"

4. PrepareData:

	In another cmd window, run the client

 	mvn -Pdata exec:java


5. Run Client App:

	cmd: mvn exec:java

 