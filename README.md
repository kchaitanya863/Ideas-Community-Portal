# Ideas-Community-Portal

This is a project to help make a simple community where people can add their ideas to make the community better.
This is the very basic version of the product.
Developed in Eclipse with backend as IBM DB2.

before running the project update the DatabaseStrings in controller package in the src folder with your computers username and password.
also create the following tables in db2,
1. users (id varchar 100(primary key), name varchar 100 , pass varchar 100);
2. ideas2 (title varchar 100(primary key), desc varchar 1000, likes integer, postedby varchar 100 (foriegn key to users.id), postedon varchar 100);
