package com.proxomoandroidsdk.definitions;

import java.util.Date;

public class Token {
	private String AccessToken;
	private double expires;
	private Date Expires;
	
	public String getAccessToken(){
		return this.AccessToken;
	}

	public double getExpires() {
		return expires;
	}

	public void setExpires(double expires) {
		this.expires = expires;
	}

	public Date getExpireDate() {
		return Expires;
	}

	public void setExpireDate(Date expireDate) {
		this.Expires = expireDate;
	}

	public void setAccessToken(String accessToken) {
		this.AccessToken = accessToken;
	}
}
