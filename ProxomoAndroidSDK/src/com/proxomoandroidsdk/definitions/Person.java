package com.proxomoandroidsdk.definitions;

import java.util.ArrayList;
import java.util.Date;

import com.proxomoandroidsdk.enums.Enums.VerificationStatus;

public class Person {
	private ArrayList<AppData> AppData;
	private String EmailAddress;
	private boolean EmailAlerts;
	private String EmailVerificationCode;
	private VerificationStatus EmailVerificationStatus;
	private boolean EmailVerified;
	private String FacebookID;
	private String FirstName;
	private String FullName;
	private String ID;
	private String ImageURL;
	private Date LastLogin;
	private String LastName;
	private boolean MobileAlerts;
	private String MobileNumber;
	private String MobileVerificationCode;
	private VerificationStatus MobileVerificationStatus;
	private boolean MobileVerified;
	private String TwitterID;
	private String UserName;
	private double UTCOffset;

	public ArrayList<AppData> getAppData() {
		return AppData;
	}

	public void setAppData(ArrayList<AppData> appData) {
		AppData = appData;
	}

	public String getEmailAddress() {
		return EmailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}

	public boolean isEmailAlerts() {
		return EmailAlerts;
	}

	public void setEmailAlerts(boolean emailAlerts) {
		EmailAlerts = emailAlerts;
	}

	public String getEmailVerificationCode() {
		return EmailVerificationCode;
	}

	public void setEmailVerificationCode(String emailVerificationCode) {
		EmailVerificationCode = emailVerificationCode;
	}

	public VerificationStatus getEmailVerificationStatus() {
		return EmailVerificationStatus;
	}

	public void setEmailVerificationStatus(
			VerificationStatus emailVerificationStatus) {
		EmailVerificationStatus = emailVerificationStatus;
	}

	public boolean isEmailVerified() {
		return EmailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		EmailVerified = emailVerified;
	}

	public String getFacebookID() {
		return FacebookID;
	}

	public void setFacebookID(String facebookID) {
		FacebookID = facebookID;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getImageURL() {
		return ImageURL;
	}

	public void setImageURL(String imageURL) {
		ImageURL = imageURL;
	}

	public Date getLastLogin() {
		return LastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		LastLogin = lastLogin;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public boolean isMobileAlerts() {
		return MobileAlerts;
	}

	public void setMobileAlerts(boolean mobileAlerts) {
		MobileAlerts = mobileAlerts;
	}

	public String getMobileNumber() {
		return MobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}

	public String getMobileVerificationCode() {
		return MobileVerificationCode;
	}

	public void setMobileVerificationCode(String mobileVerificationCode) {
		MobileVerificationCode = mobileVerificationCode;
	}

	public VerificationStatus getMobileVerificationStatus() {
		return MobileVerificationStatus;
	}

	public void setMobileVerificationStatus(
			VerificationStatus mobileVerificationStatus) {
		MobileVerificationStatus = mobileVerificationStatus;
	}

	public boolean isMobileVerified() {
		return MobileVerified;
	}

	public void setMobileVerified(boolean mobileVerified) {
		MobileVerified = mobileVerified;
	}

	public String getTwitterID() {
		return TwitterID;
	}

	public void setTwitterID(String twitterID) {
		TwitterID = twitterID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public double getUTCOffset() {
		return UTCOffset;
	}

	public void setUTCOffset(double uTCOffset) {
		UTCOffset = uTCOffset;
	}
}
