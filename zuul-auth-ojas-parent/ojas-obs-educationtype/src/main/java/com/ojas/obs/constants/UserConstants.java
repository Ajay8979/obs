package com.ojas.obs.constants;

public class UserConstants {
	public static final String SAVE = "save";

	public static final String UPDATE = "update";

	public static final String DELETE = "delete";

	public static final String GETALL = "getAll";

	public static final String GET = "getById";
	
	public static final String EDUCATIONTYPE="/obseducationtype";
	

	public static String INSERTEMPLOYEEEDUCATIONINFOSTMT = "INSERT INTO ojas_obs.obs_educationtype (educationType) VALUES (?)";

	public static String SHOWEDUCATIONINFOSTMT = "SELECT * FROM ojas_obs.obs_educationtype WHERE id=?";

	public static String UPDATESTMT = "UPDATE ojas_obs.obs_educationtype set educationType=? WHERE id = ? ";

	public static final String DELETEEDUCATION = "delete from ojas_obs.obs_educationtype where id = ?";

	public static final String TOTALRECORDS = "select * from ojas_obs.obs_educationtype";

	public static final String COUNTRECORDS = "select count(*) from ojas_obs.obs_educationtype";


}
