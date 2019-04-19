
#////// ---------------    EmployeeExperienceDetails ----------------------------
# CREATE TABLE obs_employee_experience_details (Id int(20)  AUTO_INCREMENT,company_name varchar(100)  NOT NULL,joining_date timestamp  NOT NULL,exit_date timestamp  NOT NULL,salary varchar(50)   NOT NULL,location varchar(100)  NOT NULL ,is_current_company tinyint(20)  NOT NULL ,employee_id int(50)  NOT NULL,Reference_1_name  varchar(200)  NOT NULL ,Reference_1_contact varchar(15)  NOT NULL ,Reference_2_name varchar(200)  NOT NULL,Reference_2_contact varchar(15)  NOT NULL,flag tinyint(20)  NOT NULL ,created_by varchar(50)  NOT NULL,updated_by varchar(50)  NOT NULL,created_date timestamp  NOT NULL,updated_date timestamp  NOT NULL,PRIMARY KEY (Id));
  insert_employee_experience_details = insert into obs_experiencedetails (company_name,joining_date,exit_date,salary,location,is_current_company,employee_id,Reference_1_name,Reference_1_contact,Reference_2_name,Reference_2_contact,flag,created_by,updated_by,created_date,updated_date)values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),now());
                                      
 update_employee_experience_details = update obs_experiencedetails set company_name=?,joining_date=?,exit_date=?,salary=?,location=?,is_current_company=?,employee_id=?,Reference_1_name=?,Reference_1_contact=?,Reference_2_name=?,Reference_2_contact=?,flag=?,updated_by=?,updated_date =now()  where id=? 
                                         
 getAll_employee_experience_details = select * from obs_experiencedetails where flag=false
   
getById_employee_experience_details =  select * from obs_experiencedetails where id =?
delete_employee_experience_details  = update obs_experiencedetails set flag =? ,updated_by=?, updated_date =now()  where id=?                                    
                                                                