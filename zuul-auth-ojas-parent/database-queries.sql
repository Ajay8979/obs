create database ojas_obs;
use ojas_obs;

//===========================app_role=======================================

CREATE TABLE `app_role` (
  `id` INT(11) NOT NULL,
  `role_name` VARCHAR(40) DEFAULT NULL,
  `role_id` INT(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

//===========================app_role_insert query=======================================

INSERT  INTO `app_role`(`id`,`role_name`,`role_id`) VALUES 
(1,'ROLE_ADMIN',1),
(2,'ROLE_USER',2);

//===========================user_role=======================================
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employeeId` varchar(100) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
//===========================obs_user_role_insert query=======================================

INSERT  INTO `user_role`(`id`,`employeeId`,`role_id`) VALUES 
(1,'1212',1),
(2,'1213',2);

//===========================obs_employmentdetails=======================================

CREATE TABLE `obs_employmentdetails` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `employee_id` varchar(45) NOT NULL,
  `joining_date` date NOT NULL,
  `resource_type` int(10) NOT NULL,
  `bond_status` tinyint(1) NOT NULL,
  `resignation_date` date DEFAULT NULL,
  `exit_date` date DEFAULT NULL,
  `separation_type` int(45) DEFAULT NULL,
  `flag` tinyint(45) DEFAULT '0',
  `created_by` varchar(45) NOT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `employee_id` (`employee_id`)
);

//===========================obs_kye=======================================


CREATE TABLE obs_kye
                      (id int AUTO_INCREMENT PRIMARY KEY,
                       KYE_Type varchar(45),
                       uan varchar(45),
                       KYE_address varchar(45),
                       passport_no varchar(45),
                       Passport_date_of_Issue Date,
                       Passport_date_of_expiry Date,
                       place_of_issue varchar(45),
                       Passport_address varchar(45),
                       employee_Id varchar(15),
 flag tinyint(2), 
                       created_by varchar(15),
                       updated_by varchar(15),
                       created_date datetime, 
                       updated_date datetime );

//===========================obs_projectdetails=======================================

CREATE TABLE obs_projectdetails (
  id int(20) NOT NULL AUTO_INCREMENT,
  project_name varchar(100) DEFAULT NULL,
  contract_id int(20) DEFAULT NULL,
  rate_id int(20) DEFAULT NULL,
  start_date timestamp NULL DEFAULT NULL,
  end_date timestamp NULL DEFAULT NULL,
  Billing_id int(20) DEFAULT NULL,
  employee_id varchar(100) DEFAULT NULL,
  project_tech_stack varchar(20) DEFAULT NULL,
  customer varchar(100) DEFAULT NULL,
  location varchar(100) DEFAULT NULL,
  gst_applicable tinyint(1) DEFAULT '0',
  project_type varchar(50) DEFAULT NULL,
  project_status varchar(50) DEFAULT NULL,
  bdm_contact varchar(100) DEFAULT NULL,
  is_internal tinyint(1) DEFAULT '0',
  flag tinyint(1) DEFAULT '0',
  created_by varchar(100) DEFAULT NULL,
  updated_by varchar(100) DEFAULT NULL,
  created_date timestamp NULL DEFAULT NULL,
  updated_date timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

//===========================obs_employeecontactinfo=======================================

CREATE TABLE `obs_employeecontactinfo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) ,
  `personal_mobileNo` VARCHAR(45),
  `alternate_mobileNo` VARCHAR(45),
  `current_Address_Line1` VARCHAR(45),
  `current_Address_Line2` VARCHAR(45),
  `current_City` VARCHAR(45),
  `current_State` VARCHAR(45),
  `current_Pin` INT(15),
  `permanent_Address_Line_1` VARCHAR(45),
  `emp_Id` VARCHAR(45),
  `created_By` VARCHAR(45),
 `updated_By` VARCHAR(45),
  `created_date` datetime,
  `updated_date`datetime,
  `flag` Tinyint(1),
  PRIMARY KEY (`id`)
);

//===========================obs_certificationdetails=======================================

CREATE TABLE `obs_certificationdetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `certification_name` varchar(45) NOT NULL,
  `Issued_by` varchar(45) NOT NULL,
  `Date_of_issue` timestamp NOT NULL,
  `employee_id` varchar(45) NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `created_date` timestamp NULL,
  `updated_date` timestamp NULL DEFAULT NULL,
  `flag` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

//===========================obs_employeeinfo=======================================

CREATE TABLE  obs_employeeinfo (
  id int(11) NOT NULL AUTO_INCREMENT,
  firstname varchar(50) DEFAULT NULL,
  middlename  varchar(50) DEFAULT NULL,
  lastname  varchar(50) DEFAULT NULL,
  status  int(11) DEFAULT NULL,
  dob date DEFAULT NULL,
  gender int(10) DEFAULT NULL,
  password  varchar(100) DEFAULT NULL,
  employeeId  varchar(50) DEFAULT NULL,
  flag  tinyint(4) DEFAULT NULL,
  createdOn  timestamp NULL DEFAULT NULL,
  updatedOn  timestamp NULL DEFAULT NULL,
  createdBy  varchar(20) DEFAULT NULL,
  updatedBy varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

//===========================obs_employeeinfo_insertQuery=======================================

INSERT  INTO `obs_employeeinfo`(`id`,`firstname`,`middlename`,`lastname`,`status`,`dob`,`gender`,`password`,`employeeId`,`flag`,`createdOn`,`updatedOn`,`createdBy`,`updatedBy`) VALUES 
(16,'ojas','kumar','sriram',1,'2015-03-31',1,'$2a$12$nzbJzQutYiniDH0YLze7UOEgQGyPwOX5iCpeQoeDkhbIPMqcoJ6eO','1212',0,'2019-04-03 15:24:21','2019-04-03 15:27:07',12,121),
(17,'ojasuser','ssdsriram','sfsaf',1,'2015-05-04',1,'$2a$12$nzbJzQutYiniDH0YLze7UOEgQGyPwOX5iCpeQoeDkhbIPMqcoJ6eO','1213',1,'2019-04-03 16:05:52','2019-04-04 15:44:57',1612,NULL);

//===========================obs_experiencedetails=======================================

  CREATE TABLE obs_experiencedetails (
    Id int(20)  AUTO_INCREMENT,
    company_name varchar(100)  NOT NULL,
    joining_date datetime  NOT NULL,
    exit_date datetime  NOT NULL,
    salary double   NOT NULL,
    location varchar(100)  NOT NULL ,
    is_current_company tinyint(1)  NOT NULL ,
    employee_id varchar(20)  NOT NULL,
    Reference_1_name  varchar(200)  NOT NULL ,
    Reference_1_contact varchar(15)  NOT NULL ,
    Reference_2_name varchar(200)  NOT NULL,
    Reference_2_contact varchar(15)  NOT NULL,
    flag tinyint(1)  NOT NULL ,
    created_by varchar(50)   NULL,
    updated_by varchar(50)   NULL,
    created_date timestamp  NOT NULL,
     updated_date timestamp  NULL,
   PRIMARY KEY (Id));

//===========================obs_dependent_details=======================================

create table obs_dependent_details
  (id	int(11) auto_increment PRIMARY KEY,
dependent_name	varchar(45),
relation	varchar(45),
date_of_birth	date,
employee_id	varchar(45),
created_by	varchar(45),
created_date	datetime,
updated_by	varchar(45),
updated_date	datetime,
flag	tinyint(4)
);

//===========================obs_budetails=======================================

create table obs_budetails(id int(20)  AUTO_INCREMENT,
employeeId varchar(45)  NOT NULL,
 sbu int(11) NOT NULL,
 status varchar(45) NOT NULL,
 createdby varchar(45)  NOT NULL,
 createddate datetime  NOT NULL,
updatedby varchar(45),
updateddate datetime,
flag tinyint(4) NOT NULL,
primary key(id));

//===========================obs_title=======================================

create table obs_title(id int(20)  AUTO_INCREMENT,
employeeId varchar(45)  NOT NULL,
role varchar(20)  NOT NULL,
title varchar(45)  NOT NULL,
createdby varchar(45)  NOT NULL,
createddate datetime  NOT NULL,
updatedby varchar(45),
updateddate datetime,
flag tinyint(4) NOT NULL,
primary key(id)
);

//===========================obs_resourcetype=======================================

CREATE TABLE `obs_resourcetype` (
  `resourcetype_id` int(10) NOT NULL AUTO_INCREMENT,
  `resourcetype_name` varchar(45) NOT NULL,
  PRIMARY KEY (`resourcetype_id`)
);

//===========================obs_designation=======================================

create table obs_designation(
id int   PRIMARY KEY AUTO_INCREMENT,
designation varchar(45)
);


//===========================obs_separationtype=======================================

create table obs_separationtype(
separationTypeId int  PRIMARY KEY AUTO_INCREMENT,
separationType varchar(45)
);

//===========================obs_empstatus=======================================


CREATE TABLE `obs_empstatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


//===========================obs_passport=======================================

CREATE TABLE `obs_passport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `centerName` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);


//===========================obs_genders=======================================

CREATE TABLE `obs_genders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gender` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

//===========================obs_states=======================================

CREATE TABLE `obs_states` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stateName` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

//===========================obs_costcenter=======================================

create table obs_costcenter(
id int(20) not null auto_increment,
costcentercode int(20) not null,
primary key(id)
);


//===========================obs_gpaplan=======================================


CREATE TABLE obs_gpaplan (
  gpaPlanId int(11) NOT NULL AUTO_INCREMENT,
  gpaPlanType varchar(30) DEFAULT NULL,
  gpaPremium double DEFAULT NULL,
  totalPremium double DEFAULT NULL,
  
  PRIMARY KEY (gpaPlanId)
);

//===========================obs_educationtype=======================================

CREATE TABLE `obs_educationtype` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `degree` VARCHAR(25) DEFAULT NULL,
  `pg` VARCHAR(25) DEFAULT NULL,
  `degree_stream` VARCHAR(25) DEFAULT NULL,
  `pg_stream` VARCHAR(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

//===========================obs_businessunit=======================================


CREATE TABLE obs_businessunit( 
id INT(10) NOT NULL AUTO_INCREMENT,
businessUnitName  VARCHAR(100) DEFAULT NULL,
 costCenterId  INT(20) NOT NULL,
 primary key(id)
); 

//===========================obs_rolemanagement=======================================


CREATE TABLE  obs_rolemanagement (
id int(10) NOT NULL AUTO_INCREMENT,
rolename varchar(30) DEFAULT NULL,
PRIMARY KEY (`id`)
);

//===========================obs_subbusinessunit=======================================

CREATE TABLE obs_subbusinessunit (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(30) DEFAULT NULL,
  costCenterId varchar(30) DEFAULT NULL,
  businessUnitId varchar(30) DEFAULT NULL,
  PRIMARY KEY (id)
);

//===========================obs_skill=======================================

create table obs_skill(id int(10) not null auto_increment,skill_name varchar(30) not null unique,primary key(id))