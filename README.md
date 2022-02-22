# JDBCExercises

# Exercise # 1 - List databases on a PostgreqSQL server

> **Create a Java Application, "DatabaseLister" that connects to the PostgreqSQL database server on localhost and list out names of all databases**

***Hint:*** 
- Download JDBC Driver library for PostgreqSQL
-- Search on internet for the above.
- Add this to the classpath
- Create an instance of *Connection* using *DriverManager* API
-- Specify the URL (with default database), username and password appropriately
- Lookup into "pg_database" table for the "datname" field.
- Iterate through the result and gather the result/values 
- Display the gathered values as the list of databases

# Exercise # 2 - List Schemas in a PostgreqSQL Database

> **Create a Java Application, "SchemaLister" that connects to the PostgreqSQL database server on localhost and list out names of all Schemas**

***Hint:*** 
- Download JDBC Driver library for PostgreqSQL
-- Search on internet for the above.
- Add this to the classpath
- Create an instance of *Connection* using *DriverManager* API
-- Specify the URL with specified database, username and password appropriately
- Lookup into "information_schema.schemata" view for the "schema_name" field.
- Iterate through the result and gather the result/values 
- Display the gathered values as the list of schemas

# Exercise # 3 - List Tables in a PostgreqSQL Database

> **Create a Java Application, "TableLister" that connects to the PostgreqSQL database on localhost and list out names of all Tables**

***Hint:*** 
- Download JDBC Driver library for PostgreqSQL
-- Search on internet for the above.
- Add this to the classpath
- Create an instance of *Connection* using *DriverManager* API
-- Specify the URL with specified database, username and password appropriately
- Get hold of *DatabaseMetaData* from the *Connection* object
- Use *getTables* method on above to find the list of tables 
-- Note: Filter out everything which is not a table, technically
- Display the gathered values as the list of Tables

# Exercise # 4 - Employee Management using JDBC

**Create a Java Application, "EmployeeManagementApp" that does the following tasks**

> **Create "Employees" table with the following details**
 
 | Column Name | Description | Data Type | Is Unique? | Is Nullable?|
 |:--- | :--- | :---: | --- | --- |
 |fname|First Name|Character (30)|No|No|
 |lname|Last Name|Character (30)|No|No|
 |email|Email ID|Character (50)|Yes|No|
 |contact|Phone Number|Integer (15)|No|Yes|

***Hint:*** use CREATE TABLE command.

> **Confirm that "Employees" table has been created correctly. List out the column names with their datatypes**

***Hint:*** 
- Execute a query on the "Employees" table to get all records.
- Inspect the *ResultSetMetaData* from above received *ResultSet* object

> **Insert the following records in the "Employees" table**

|fname|lname|email|
|---|---|---|
|Adam|Falon|adam.falon@dci.com|
|Mary|Gold|mary.gold@dci.com|
|Adam|Currie|adam.currie@dci.com|
|Bryan|Jhonson|bryan.Jhonson@dci.com|
|Prasad|Ritesh|prasad.ritesh@dci.com|
|Mary|Jhonson|mary.jhonson@dci.com|

***Hint:*** 

- Create a *PreparedStatement* object for the *INSERT INTO* command
- Build a batch of queries for each Employee record listed above
- Execute the batch at the end

> **Confirm that all records listed above were inserted into the "Employees" table**

***Hint:*** 

- Use *SELECT* command to find all records from the Employees table
- Confirm that the number of loaded records is equal to the number of records inserted above.

