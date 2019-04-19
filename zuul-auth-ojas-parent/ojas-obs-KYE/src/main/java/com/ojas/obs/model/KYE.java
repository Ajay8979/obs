package com.ojas.obs.model;

/**
 * 
 * @author tshiva
 *
 */
public class KYE {

	private int id;
	private String kYE_Type;
	private String uan;
	private String kYE_address;
	private String passport_no;
	private String passport_date_of_Issue;
	private String passport_date_of_expiry;
	private String place_of_issue;
	private String passport_address;
	private String employee_Id;
	private boolean flag;
	private String created_by;
	private String updated_by;
	private String created_date;
	private String updated_date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getkYE_Type() {
		return kYE_Type;
	}

	public void setkYE_Type(String kYE_Type) {
		this.kYE_Type = kYE_Type;
	}

	public String getUan() {
		return uan;
	}

	public void setUan(String uan) {
		this.uan = uan;
	}

	public String getkYE_address() {
		return kYE_address;
	}

	public void setkYE_address(String kYE_address) {
		this.kYE_address = kYE_address;
	}

	public String getPassport_no() {
		return passport_no;
	}

	public void setPassport_no(String passport_no) {
		this.passport_no = passport_no;
	}

	public String getPassport_date_of_Issue() {
		return passport_date_of_Issue;
	}

	public void setPassport_date_of_Issue(String passport_date_of_Issue) {
		this.passport_date_of_Issue = passport_date_of_Issue;
	}

	public String getPassport_date_of_expiry() {
		return passport_date_of_expiry;
	}

	public void setPassport_date_of_expiry(String passport_date_of_expiry) {
		this.passport_date_of_expiry = passport_date_of_expiry;
	}

	public String getPlace_of_issue() {
		return place_of_issue;
	}

	public void setPlace_of_issue(String place_of_issue) {
		this.place_of_issue = place_of_issue;
	}

	public String getPassport_address() {
		return passport_address;
	}

	public void setPassport_address(String passport_address) {
		this.passport_address = passport_address;
	}

	public String getEmployee_Id() {
		return employee_Id;
	}

	public void setEmployee_Id(String employee_Id) {
		this.employee_Id = employee_Id;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created_by == null) ? 0 : created_by.hashCode());
		result = prime * result + ((created_date == null) ? 0 : created_date.hashCode());
		result = prime * result + ((employee_Id == null) ? 0 : employee_Id.hashCode());
		result = prime * result + (flag ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((kYE_Type == null) ? 0 : kYE_Type.hashCode());
		result = prime * result + ((kYE_address == null) ? 0 : kYE_address.hashCode());
		result = prime * result + ((passport_address == null) ? 0 : passport_address.hashCode());
		result = prime * result + ((passport_date_of_Issue == null) ? 0 : passport_date_of_Issue.hashCode());
		result = prime * result + ((passport_date_of_expiry == null) ? 0 : passport_date_of_expiry.hashCode());
		result = prime * result + ((passport_no == null) ? 0 : passport_no.hashCode());
		result = prime * result + ((place_of_issue == null) ? 0 : place_of_issue.hashCode());
		result = prime * result + ((uan == null) ? 0 : uan.hashCode());
		result = prime * result + ((updated_by == null) ? 0 : updated_by.hashCode());
		result = prime * result + ((updated_date == null) ? 0 : updated_date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KYE other = (KYE) obj;
		if (created_by == null) {
			if (other.created_by != null)
				return false;
		} else if (!created_by.equals(other.created_by))
			return false;
		if (created_date == null) {
			if (other.created_date != null)
				return false;
		} else if (!created_date.equals(other.created_date))
			return false;
		if (employee_Id == null) {
			if (other.employee_Id != null)
				return false;
		} else if (!employee_Id.equals(other.employee_Id))
			return false;
		if (flag != other.flag)
			return false;
		if (id != other.id)
			return false;
		if (kYE_Type == null) {
			if (other.kYE_Type != null)
				return false;
		} else if (!kYE_Type.equals(other.kYE_Type))
			return false;
		if (kYE_address == null) {
			if (other.kYE_address != null)
				return false;
		} else if (!kYE_address.equals(other.kYE_address))
			return false;
		if (passport_address == null) {
			if (other.passport_address != null)
				return false;
		} else if (!passport_address.equals(other.passport_address))
			return false;
		if (passport_date_of_Issue == null) {
			if (other.passport_date_of_Issue != null)
				return false;
		} else if (!passport_date_of_Issue.equals(other.passport_date_of_Issue))
			return false;
		if (passport_date_of_expiry == null) {
			if (other.passport_date_of_expiry != null)
				return false;
		} else if (!passport_date_of_expiry.equals(other.passport_date_of_expiry))
			return false;
		if (passport_no == null) {
			if (other.passport_no != null)
				return false;
		} else if (!passport_no.equals(other.passport_no))
			return false;
		if (place_of_issue == null) {
			if (other.place_of_issue != null)
				return false;
		} else if (!place_of_issue.equals(other.place_of_issue))
			return false;
		if (uan == null) {
			if (other.uan != null)
				return false;
		} else if (!uan.equals(other.uan))
			return false;
		if (updated_by == null) {
			if (other.updated_by != null)
				return false;
		} else if (!updated_by.equals(other.updated_by))
			return false;
		if (updated_date == null) {
			if (other.updated_date != null)
				return false;
		} else if (!updated_date.equals(other.updated_date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KYE [id=" + id + ", kYE_Type=" + kYE_Type + ", uan=" + uan + ", kYE_address=" + kYE_address
				+ ", passport_no=" + passport_no + ", passport_date_of_Issue=" + passport_date_of_Issue
				+ ", passport_date_of_expiry=" + passport_date_of_expiry + ", place_of_issue=" + place_of_issue
				+ ", passport_address=" + passport_address + ", employee_Id=" + employee_Id + ", flag=" + flag
				+ ", created_by=" + created_by + ", updated_by=" + updated_by + ", created_date=" + created_date
				+ ", updated_date=" + updated_date + "]";
	}

}
