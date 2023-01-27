# Management
First we created a Database name as managementnew in Mysql using XAMPP phpmyadmin and run the java application in the Management using spring boot.
Now,we can see the columns names in the database and then create the employee table with csv format import it into the database table employee.
![Database page](https://user-images.githubusercontent.com/121932305/215082001-a006f017-22a8-48cb-a1d3-4530df138157.jpg)
In src/main/resources/templates/index.html ,we have given button as click here to continue which looks same as below picture.After login it will redirect to Emp login page.
![index page](https://user-images.githubusercontent.com/121932305/215082447-44bf82c2-f0cd-4bd9-a2b8-f384633e399c.jpg)
The code for the below pages is located in src/main/resources/templates/emp.
After submit the credentials of required employee it will redirect to emp dashboard there we can see the details of the employee(empdashboard.html) and clicking on edit option will redirect to next page editemployees(edit_emp.html)and clicking on home brings back to home page of emp dashboard and logout to emp login page.
![Emp pages](https://user-images.githubusercontent.com/121932305/215082471-049652af-a67a-48e9-9609-d834e2747446.jpg)
The code for the below pages is located in src/main/resources/templates/hr.
After submit the credentials of required hr admin it will redirect to hr dashboard there we can see the details of the hr(hrdashboard.html) and clicking on edit option will redirect to next page editemployees(edit_emp.html)and clicking on Employee deatisl hr can view all the employee details stored in the database and home brings back to home page of emp dashboard and logout to emp login page.
![Hr pages](https://user-images.githubusercontent.com/121932305/215082483-a2df0771-3ca0-4307-84bd-17d354eb027f.jpg)


