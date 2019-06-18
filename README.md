REQUIREMENT
--------------
Data for the project is included in the sample CSV file, attached. Please create a Github or Bitbucket repository for the project and push all code there; please email the link to the repository when you submit your project.

Customer X just informed us that we need to churn out a code enhancement ASAP for a new project. Here is what they need:

We need a Java application that will consume a CSV file, parse the data and insert to a SQLite In-Memory database.
a. Table X has 10 columns A, B, C, D, E, F, G, H, I, J which correspond with the CSV file column header names.

b. Include all DDL in submitted repository

c. Create your own SQLite DB

The datasets can be extremely large so be sure the processing is optimized with efficiency in mind.

Each record needs to be verified to contain the right number of data elements to match the columns.

a. Records that do not match the column count must be written to the bad-data-.CSV file

b. Elements with commas will be double quoted

At the end of the process write statistics to a log file
a. # of records received

b. # of records successful

c. # of records failed

Rules and guidelines:

Feel free to use any online resources

Utilizing existing tools like Maven and open source libraries is encouraged.

A finished solution is great but if you do not get it all completed, that is ok - we will evaluate based on the approach

It is required that you provide a README detailing the challenge

DETAILING THE CHALLENGE
---------------------------
Step1: Analysed the given requirement.
Step2: Created new repository naming CSVtoSQLite in GitHub so that I can push all my project related code here.
Step3: As per my requirement, I have to create my own SQLite DB.
SQLite is an open source, in-memory library that you can call and use directly. No installation and no configuration required. From the SQLite official website in the download section, I download the package command-line shell program. Command-Line Program (CLP) is a command line application that let you access the SQLite database management system and all the features of the SQLite. Using CLP, we can create and manage the SQLite database.
SQLite Studio: It is a portable tool that doesn't require an installation. It supports both SQLite3 and SQLite2. We can easily import and export data to various formats like CSV, HTML, PDF, JSON. Its open source and supports Unicode.

I have build Maven Project in Eclipse IDE
------------------------------------------
Copied ms3Interview.csv file to resources.
Downloaded SQLite JDBC library and configured it in pom.xml file
<dependencies>
		<!-- https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc -->
		<dependency>
		    <groupId>org.xerial</groupId>
		    <artifactId>sqlite-jdbc</artifactId>
		    <version>3.27.2.1</version>
		</dependency>

		<dependency>
			<groupId>com.opencsv</groupId>
			<artifactId>opencsv</artifactId>
			<version>4.0</version>
		</dependency>
	</dependencies>


DDL
-----
To Create an empty Database at the SQLite CommandLine:
-------------------------------------------------------
sqlite>sqlite3 test.db

To Create an empty Table 
-------------------------------------------------------
create table Interview(A string, B string, C string, D string, E string, F string, G string, H string, I string, J string);

To insert values into table Interview:
---------------------------------------
insert into Interview(A, B, C, D, E, F, G, H, I, J) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
