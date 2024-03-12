# Java Library Management System

This project is a library management system built using Java. 

## Table of Contents

1. About
2. Visitor Functionality
3. Member Functionality
4. Admin Functionality
5. Key Code

## 1. About:

This system is a console application the allows the user to see information about the books depending on status. the three types of users are:

1. Visitor
2. Admin - which requires a login.
3. Member - which requires a login. 

The users, admins and loaned books lists are all persistent as the application writes a json file for this data. The source data for the original list of books in the library is stored in the books.csv file.

## 2. Visitor Functionality

A visitor is an unregistered user of the library. They can do three things:

* See a lists of the books in the library.
* Read a book at the library.
* Become a member of the library.

## 3. Member Functionality

A member is a user of the library that has registered an account. A member can do what a visitor can as well as:

* Loan a book.
* If the user already has that book, they are unable to take out the loan.

## 4. Admin Functionality

An admin would be the access of a memeber of staff at the library. An admin can do the following things:

* Run a list of books listed in the library.
* Run a report on the books currently on loan.
* Add another admin member.






