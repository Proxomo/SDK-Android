package com.proxomoandroidsdk.definitions;

import java.util.Date;

public class Token {
	private String accessToken;
	private double expires;
	private Date expireDate;
	
	public String getAccessToken(){
		return this.accessToken;
	}

	public double getExpires() {
		return expires;
	}

	public void setExpires(double expires) {
		this.expires = expires;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
