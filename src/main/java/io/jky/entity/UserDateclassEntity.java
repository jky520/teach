package io.jky.entity;

import java.io.Serializable;
/**
 * 
 * @author jky
 *
 */
public class UserDateclassEntity implements Serializable {

	private static final long serialVersionUID = 1514942097366882365L;
	
	private Long userId;
	
	private Long dcId;
	
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

}
