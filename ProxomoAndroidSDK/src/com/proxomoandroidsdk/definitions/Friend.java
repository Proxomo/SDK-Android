package com.proxomoandroidsdk.definitions;

import com.proxomoandroidsdk.enums.Enums.FriendStatus;

public class Friend {
	private String PersonID;
	private String FirstName;
	private String LastName;
	private String FullName;
	private FriendStatus FriendStatus;
	private String TwitterID;
	private String FacebookID;
	private String ImageURL;

	public String getPersonID() {
		return PersonID;
	}

	public void setPersonID(String personID) {
		PersonID = personID;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public FriendStatus getFriendStatus() {
		return FriendStatus;
	}

	public void setFriendStatus(FriendStatus friendStatus) {
		FriendStatus = friendStatus;
	}

	public String getTwitterID() {
		return TwitterID;
	}

	public void setTwitterID(String twitterID) {
		TwitterID = twitterID;
	}

	public String getFacebookID() {
		return FacebookID;
	}

	public void setFacebookID(String facebookID) {
		FacebookID = facebookID;
	}

	public String getImageURL() {
		return ImageURL;
	}

	public void setImageURL(String imageURL) {
		ImageURL = imageURL;
	}

}
