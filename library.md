# Post Course – Java Project

### Overview

Over the last two weeks we’ve consolidated React & Spring Boot, now it’s time to fine tune your Java Skills.
This week’s challenge is to build a library management system. You will be provided with the data you need.

### Goals

- A functioning Library System: You will build a console application that allows the user to see what books (data provided) the library currently has available. The data will be provided in CSV format, parse this into JSON format and write the data to a JSON file.  
  This should be done using Java and not done manually. The idea is to add persistence to your application by reading/writing to a file.
- Loan System: A user should be able to loan a book and also see what books they currently have loaned. A user should not be able to loan a book that is already out on loan. Write to a new JSON file to make the users persistent.
- Reports: A library admin should be able to run a report that shows all the books currently out on loan and also the amount of times a particular books have been loaned out.
- Ext. Can you output the report in CSV format?

---

### Specifications

Make the following additions to your project:

- You need to have it as a repository on GitHub
- The data will be provided to you in CSV format, you will need to research how to use this in your java application.
- You will need to make use of multiple classes, think about how to best approach this, e.g. a User & Book class.
- All user interaction should take place in the console.
- Users should be able to create an account and users & admins should have different privileges, e.g. users cannot run reports
