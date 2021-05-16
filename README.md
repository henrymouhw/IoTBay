# IoTBay
### The following description based on the submission zip.
#Prerequisites:
- NetBeans 8.2 with GlassFish Server
- You cloud download from the following link
- https://netbeans.apache.org/download/index.html

#Deploy Web Application
1. Open NetBeans
2. Select the services tab
3. Expand "Servers"
4. Click GlassFish Server and select "Start"
5. After the GlassFish start, click again and click view domain console
6. Click "Deploy an application" under the "Deployment" heading
7. Under location click browse and select the WAR file
8. Click "OK" and on the now page click "Launch"
9. Click the first link and the system will redirected you to the web application

#Deploy Web Application from NetBeans
1. Open NetBeans
2. Select file and open project
3. Click "Run Project"to build and run the website.
4. Browser will pop up on your local GlassFish server with the new project's URL

# View Database
1. Open NetBeans
2. Select the Services tab
3. Expand Databases
4. Right click “Java DB” and select “Create Database”
5. Enter “IoTBayDB_G45Ass2”as the database name and “Group45”as the username, “admin” as the password, then click “ok”.
6. Look for jdbc:derby://localhost:1527/ioTBayDb_G45Ass2 [Group45 on Group45]
7. Right click and click “connect”
8. Open the db folder under “Web Pages , open the sql file one by one. Right click and select “run file”
9. The db folder have two type: Create and populate, Create is to create table, populate is to fill with data in respect table.
10. The Populate type file must run in order (Account-first, product-second,CREcord-third,Staff-fourth)
11. In the connection prompt, select the database (details is in step 5)
