package com.proxomoandroidsdk.definitions;

import java.util.Date;

import com.proxomoandroidsdk.enums.Enums.EventParticipantStatus;

public class EventParticipant {
	private String PersonID;
	private String ID;
	private String PersonName;
	private String ImageURL;
	private String EventID;
	private Date LastUpdate;
	private EventParticipantStatus Status;

	public String getPersonID() {
		return PersonID;
	}

	public void setPersonID(String personID) {
		PersonID = personID;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPersonName() {
		return PersonName;
	}

	public void setPersonName(String personName) {
		PersonName = personName;
	}

	public String getImageURL() {
		return ImageURL;
	}

	public void setImageURL(String imageURL) {
		ImageURL = imageURL;
	}

	public String getEventID() {
		return EventID;
	}

	public void setEventID(String eventID) {
		EventID = eventID;
	}

	public Date getLastUpdate() {
		return LastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		LastUpdate = lastUpdate;
	}

	public EventParticipantStatus getStatus() {
		return Status;
	}

	public void setStatus(EventParticipantStatus status) {
		Status = status;
	}

}
