**Complaint Management System:**

Develop a REST API to manage users, complaints,

departments, employees, and complaint status records with CRUD operations, database

connectivity, and relationships among the entities.





1.user creates the complaint which belongs to a particular department.

2.Employees of the particular department handle the compliant and update its status.

3.Each user can post multiple compliant but each compliant belong to a particular user.

4.Each department has multiple employee but each employee belongs to a particular department.

5.multiple employees can handle a single compliant.

6.A single employee maybe involved with multiple compliant.

7.every complaint is given by a user but not all users give complaint.

8.there is no relation between user and department entities.

9.every employee belongs to a department but not every department has employees.

10.Many compliant belong to a single department but not all departments have complaints.

11.total participation from complaint but partial participation from department.

12.one complaint can exist in multiple states but a complaint state belongs to a single complaint so 1 to n(complaint to status).

13.every complaint has a status and every status belongs to a complaint so total participation from both sides.

14.nothing between department and complaint status record.

15.mappedBy tells JPA that this side is the inverse/non-owning side, and that the other side manages the relationship. Therefore, this side does not create/manage a separate FK or relationship mapping.





**Database Design:**

&#x09;**Entities:**

&#x09;	user table:

&#x09;		1.user\_Id (primary key)

&#x09;		2.user name

&#x09;		3.user Email





&#x09;	Complaint table:

&#x09;		1.Complaint\_Id (primary key)

&#x09;		2.Complaint description

&#x09;		3.user\_Id (foreign key reference from user table)

&#x09;		4.Department\_Id (foreign key reference from Department table)

&#x09;



&#x09;	Department table:

&#x09;		1.Department\_Id (primary key)

&#x09;		2.Department name





&#x09;	Employee table:

&#x09;		1.Employee\_Id (primary key)

&#x09;		2.Employee name

&#x09;		3.Department\_Id (foreign key reference from Department table)



&#x09;	Complaint Status Record Table:

&#x09;		1.Status\_Id (primary key)

&#x09;		2.Status Type (Open, InProgress, resolved, closed)

&#x09;		3.Complaint\_Id (foreign key reference from Compliant table)

&#x09;

&#x09;**Relationship:**

&#x09;	compliant-Employee Table:

&#x09;		1.compliant\_Id (foreign key reference from compliant table)

&#x09;		2.Employee\_Id (foreign key reference from employee table)







**JPA Enitity Relationship:**

&#x09;1.User table to Compliant (OneToMany) (joinColumn goes in complaint table) (owning side is complaint).

&#x09;2.Complaint to employee(ManytoMany) (join table can be used to join the respective pks) (owning side can be anything).

&#x09;3.Department to Employee(OneToMany) (joincolumn goes at Employee table) (owning side is the Employee).

&#x09;4.Department to Complaint(OneToMany) (JoinCoulumn goes in complaint table) (owning side is the compliant).

&#x09;5.Compliant to Compliant status record (OneToMany) (join column goes in compliant status record) (owning side is the complaint status).







**Dependencies:**

&#x09;1.Spring web

&#x09;2.mysql driver

&#x09;3.spring data JPA.

&#x09;



&#x09;



&#x09;

