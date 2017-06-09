package io.jky.entity;

import java.io.Serializable;

public class DateclassRegistEntity implements Serializable {
	
	private static final long serialVersionUID = -869157637157368273L;
	private Long userId;
	private Long dcId;
	private Long crId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getDcId() {
		return dcId;
	}
	public void setDcId(Long dcId) {
		this.dcId = dcId;
	}
	public Long getCrId() {
		return crId;
	}
	public void setCrId(Long crId) {
		this.crId = crId;
	}
	
}
