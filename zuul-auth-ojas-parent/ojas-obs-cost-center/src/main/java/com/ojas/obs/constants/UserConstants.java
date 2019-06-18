package com.ojas.obs.constants;

public class UserConstants {
	//#------------------SQL Queries--------------------------

	public static final String INSERTCOSTCENTER = "insert into obs_costcenter(costcentercode) values (?)";
	public static final String UPDATECOSTCENTER = "update obs_costcenter set costcentercode= ? where id= ?";
	public static final String DELETECOSTCENTER = "delete from obs_costcenter where id= ?";
	public static final String GETCOSTCENTER = "select * from obs_costcenter";
	public static final String GETCOSTCENTERCOUNT ="select count(*) from obs_costcenter";

}
