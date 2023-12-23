汽车租赁数据库管理系统---springboot+vue+postgresql
springboot 2.7.6
vue3
postgresql14.2
vue前端组件使用的是ElementPlus

页面展示：
![image](https://github.com/vivia12/--springboot-vue/assets/105705567/75547d15-2e7c-4093-b4ef-87f4f4d9b262)



项目背景：
Database Design: Using MySQL and PHP to
Implement the Car Share Reservation System

This project is to use MySQL and PHP to implement the RBMS application. In this case, you’ll design a relational database for a car share business. After your database design is completed and correct, you will create database tables and populate them with data. Customers will book and pick the cars on your website. So you will produce a website will display the cars available for rent and with some queries and reports. You will produce a form with a subform, five queries, a report, and a custom navigation pane. The form will record the reservations for each car. The queries will display customers who are students with driving violations, popular pickup locations, cars that hold a certain number of passengers, and rental trends. Another query will allow the company to increase the rental price of its vehicles. The report will summarize rentals for the month so far, along with the amount of money charged to each customer. The custom navigation pane will allow access to all tables, forms, queries, and the report. An implement of Web interactive interface using PHP is needed which ensure users of your system can use your project conveniently.


BACKGROUND

Atlanta Car Share(ACS) is a company that rents cars by the hour or day to Atlanta residents. The model is simple: Customers register for the service, book their reservations on the Web, and pick up their car from one of three areas in the city. They are given a code to open a central pod where they can find the key to their car. The car comes with at least a quarter-tank of gasoline and a credit card for further fill-ups. The cost of sharing the car therefore includes the gas used. Prices for sharing cars begin at $3.90 an hour and $39.00 per day. The price also includes insurance for the car. 
You have been hired to create a reservation database for ACS. You must keep several parameters in mind when designing the database. When potential customers register for ACS, they need to provide a certain amount of information and indicate whether they are students. The required information includes full name, address, telephone number, cell phone number, e-mail address, and credit card number. Customers need to divulge any tickets or infractions on their driving record, along with a current driver’s license number, state of issue, and expiration date.
ACS has a variety of cars available for sharing. The cars vary by make, model, and rental price. Customers often want to search for cars by size, so it’s important to note the maximum number of people that can comfortably fit into the car. ACS has three central locations for picking up and dropping off cars. When customers reserve a car, they note the location code for picking up and dropping off. It would be convenient to have a form with a subform to record customer reservations.
ACS management would like to have a listing of customers who are students and who have had violations over the past three years, such as running a red light. Those customers would be put on probation, which is a period of watching their driving habits in the shared cars more closely than normal. A query could provide this listing.
Customers often telephone the main office of ACS and want to know how many passengers a car will hold. The management wants you to set up a query to answer that question. The query would prompt the user to enter the number of passengers desired, and the output would display all vehicles that could handle that number.

ACS management would like to do some data analysis to plan for future expansion. First, they want you to create a query that lists the most popular pickup locations for cars. They suspect that one location is the customers’ favorite, but they want to confirm that suspicion. In addition, they want to know which types of customers are sharing which types of cars. In particular, they want the data separated into student and nonstudent groups.
Fuel costs have skyrocketed recently, so ACS would like to raise the price of sharing all vehicles by $0.50 per hour and $5.00 per day. ACS wants you to create an update query to increase the prices. The management likes to get periodic reports that show how the company is doing. You’ll need to create a report for the current month that shows the rental reservations and the potential income from each reservation. Finally, a custom navigation pane on Web should be set up to make it easy to work with the database
1.  Building the database (15 points)

Use the SQL DDL statements to create the tables required for this project. Please also note that the tables are created in certain order such that by the time when a foreign key needs to be created, the corresponding primary key also need to be created.

(1) First, determine the tables you’ll need by listing them on paper. List the name of each table and
the fields it should contain. Avoid data redundancy. Do not create a field if it could be created by
a “calculated field” in a query .
(2)You will need transaction tables (Rentals). Think about what business events occur with each customer’s transactions. Avoid duplicating data.
(3)You must mark the appropriate primary key field(s) or foreign key field(s) for each table.
Keep in mind that each table might need a compound primary key to uniquely identify a record within a table.
(4) Enter at least 10 records for cars with different manufacturers and numbers of passengers.
(5) Enter records for at least 10 customers, including their names, addresses, telephone numbers,
e-mail addresses and etc. Enter your own name and information as an additional customer.
(6)Each car should be rented at least once. Each customer should rent a car twice. Appropriately limit the size of the text fields; for example, a telephone number does not need the default length of 255 characters.

2.  MySQL Implementation (55 points)

You need to write SQL queries, stored procedures/functions, and triggers to implement this project. The following requirements and functionalities need to be implemented.
(1)(8 points) Write a stored procedure called called Customers on Probation that filters all customers who are students and who have had tickets in the last three years. The query should include columns for Last Name, First Name, and Email Address. Depending on the data, the query output might resemble that in table 1.
Table 1 Customers on Probation
Customers on Probation
Last Name	First Name	Email Address
Murray	Annabelle	belle@comcast.net
Quinn	Sean	quinn45@gmail.com
Smith	Patricia	patti1@gmail.com


(2) (9 points) Write a stored procedure called Number of Passengers. This query should prompt the user for the number of passengers required in their shared car. The query should display columns for Car ID, Make, Model, Price Per Hour, and Number of Passengers. The number of passengers should meet the required number specified. Your data will differ, but if you enter data for four or more required passengers, your output should resemble that in table 2.
Table 2  Number of Passengers
Number of Passengers
Car ID	Make	Model	Price Per Hour	Number of Passengers
101	Subaru	Impreza	$3.90	5
102	Lexus	IS250	$5.00	5
104	Toyota	Prius Liftback	$5.50	5
105	Honda	Element	$3.90	5


(3) (12 points) Write a stored procedure called Popular Locations. Using the functionality of the query, determine how many rentals have occurred in each location. The query should include columns for Location ID, Street Address, Telephone, and Number of Rentals. Note that the column headings are a change from the default settings provided by the query generator. Your data will differ, but the output should resemble that in table 3. 
Table 3  Popular Locations
Popular Locations
Location ID	Street Address	Telephone	Number of Rentals
60	800 Cherokee Drive	404-776-1022	8
59	1400 W Peachtree NE	404-897-0021	2
61	2238 Perkerson Road	404-223-1056	1



(4) (12 points) Write a stored procedure called Rental Trends. Again, using the functionality of the query, determine the number of times each model has been rented. The query should include the columns Make, Model, Student?, and Number of Times Rented. Sort the query by Student. Note that the column headings are a change from the default settings provided by the query generator. Your output should resemble that in table 4, with different data. 
Table 4 Rental Trends
Popular Locations
Make	Model	Student?	Number of Times Rented
Lexus	IS250		2
Smart	Passion		2
Subaru	Impreza		1
Honda	Element	□	2
Subaru	Impreza	□	3
Toyota	Prius Liftback	□	1


(5)(14 points) Create an update query called Increased Price that adds $0.50 to each rental price per hour and $5.00 to each rental price per day. Run the query to test it.

3. Interface (20 points) 
 
Implement a Web interactive interface using PHP 、JavaScript etc . Your interface program should utilize as many of your MySQL stored procedures/functions as possible.

4. Documentation (10 points)

Documentation consists of the following aspects:

(1)Each procedure and function and every other object you create for your project needs to be explained clearly regarding its objective and usage.
(2)Your code needs to be well documented with in-line comments.

5.  Hand-ins, Demo and Grading 

(1)You will also need to submit your source code along with your documentation to the Blackboard.
(2)It is required to demonstrate your project to the instructor using tuples created by the instructor. 
(3)The grading will be based on the quality of your code, the documentation and on how successful of your demo is.
