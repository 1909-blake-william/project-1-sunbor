package com.revature.model;

public class UpdateData {
	private int reimbId;
	private int statusId;
	private int resolverId;
	public UpdateData() {
		// TODO Auto-generated constructor stub
	}
	public UpdateData(int reimbId, int statusId, int resolverId) {
		super();
		this.reimbId = reimbId;
		this.statusId = statusId;
		this.resolverId = resolverId;
	}
	public int getReimbId() {
		return reimbId;
	}
	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getResolverId() {
		return resolverId;
	}
	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reimbId;
		result = prime * result + resolverId;
		result = prime * result + statusId;
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
		UpdateData other = (UpdateData) obj;
		if (reimbId != other.reimbId)
			return false;
		if (resolverId != other.resolverId)
			return false;
		if (statusId != other.statusId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UpdateData [reimbId=" + reimbId + ", statusId=" + statusId + ", resolverId=" + resolverId + "]";
	}
	
	

}
