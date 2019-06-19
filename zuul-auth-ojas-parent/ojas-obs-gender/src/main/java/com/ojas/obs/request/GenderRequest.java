package com.ojas.obs.request;

import java.util.List;

import com.ojas.obs.model.Genders;

public class GenderRequest {
  private List<Genders> gender;
  private String transactionType;
public List<Genders> getGender() {
	return gender;
}
public void setGender(List<Genders> gender) {
	this.gender = gender;
}
public String getTransactionType() {
	return transactionType;
}
public void setTransactionType(String transactionType) {
	this.transactionType = transactionType;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((gender == null) ? 0 : gender.hashCode());
	result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
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
	GenderRequest other = (GenderRequest) obj;
	if (gender == null) {
		if (other.gender != null)
			return false;
	} else if (!gender.equals(other.gender))
		return false;
	if (transactionType == null) {
		if (other.transactionType != null)
			return false;
	} else if (!transactionType.equals(other.transactionType))
		return false;
	return true;
}


}
