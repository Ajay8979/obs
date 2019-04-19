

INSERT_EMPLOYMENT_DETAILS_STMT = INSERT into employment_details(employee_id,joining_date,resource_type,bond_status,flag,created_by,created_date) values(?,?,?,?,?,?,now())

 GET_EMPLOYMENT_DETAILS_STMT = select * from employment_details where flag=1

 UPDATE_EMPLOYMENT_DETAILS_STMT = UPDATE employment_details set resource_type=?,bond_status=?,resignation_date=?,exit_date=?,separation_type=?,flag=?,updated_by=?,updated_date=now() where id=? 

DELETE_EMPLOYMENT_DETAILS_STMT = UPDATE employment_details set flag=?,updated_by=?,updated_date=now() where id=?

GET_EMPLOYMENT_DETAILS_BY_EMPLOYEE_ID_STMT= select * from employment_details where employee_id=? and flag=1

GET_EMPLOYMENT_DETAILS_BY_ID_STMT=select * from employment_details where id=? and flag=1
