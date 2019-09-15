AdminLogin
CREATE TABLE AdminLogin(
   Username VARCHAR2(50),
   pass_word VARCHAR2(25)
)
    
INSERT INTO AdminLogin VALUES ( 'Subham' , '1234')
    
EmployeeLogin
CREATE TABLE EmployeeLogin(
id NUMBER(5),
username VARCHAR2(50),   
email VARCHAR2(50),
phonenumber NUMBER(10),
pass_word VARCHAR2(25)
)

INSERT INTO EmployeeLogin VALUES(1, 'Subham', '120198subham@gmail.com', 9030346531, '1234')
    
SELECT * FROM EmployeeLogin