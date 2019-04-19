package com.ojas.obs.model;
/**
 * 
 * @author mpraneethguptha
 *
 */
public class EmployeeEducation {

	private Integer id;
	private String degree;
	private String pg;
	private String degree_stream;
	private String pg_stream;

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getPg() {
		return pg;
	}

	public void setPg(String pg) {
		this.pg = pg;
	}

	public String getDegree_stream() {
		return degree_stream;
	}

	public void setDegree_stream(String degree_stream) {
		this.degree_stream = degree_stream;
	}

	public String getPg_stream() {
		return pg_stream;
	}

	public void setPg_stream(String pg_stream) {
		this.pg_stream = pg_stream;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "EmployeeEducation [id=" + id + ", degree=" + degree + ", pg=" + pg + ", degree_stream=" + degree_stream
				+ ", pg_stream=" + pg_stream + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((degree == null) ? 0 : degree.hashCode());
		result = prime * result + ((degree_stream == null) ? 0 : degree_stream.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pg == null) ? 0 : pg.hashCode());
		result = prime * result + ((pg_stream == null) ? 0 : pg_stream.hashCode());
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
		EmployeeEducation other = (EmployeeEducation) obj;
		if (degree == null) {
			if (other.degree != null)
				return false;
		} else if (!degree.equals(other.degree))
			return false;
		if (degree_stream == null) {
			if (other.degree_stream != null)
				return false;
		} else if (!degree_stream.equals(other.degree_stream))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pg == null) {
			if (other.pg != null)
				return false;
		} else if (!pg.equals(other.pg))
			return false;
		if (pg_stream == null) {
			if (other.pg_stream != null)
				return false;
		} else if (!pg_stream.equals(other.pg_stream))
			return false;
		return true;
	}

}
