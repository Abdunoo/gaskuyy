package com.abdun.dto;

import jakarta.enterprise.context.RequestScoped;

/**
 *
 * @author abdun
 */
@RequestScoped
public class CurrentUser {
	private int userId;
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

}
