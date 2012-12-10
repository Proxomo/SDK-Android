package com.proxomoandroidsdk.definitions;

import java.util.ArrayList;
import java.util.Date;

import com.proxomoandroidsdk.enums.Enums.EventPrivacy;
import com.proxomoandroidsdk.enums.Enums.EventStatus;

public class Event {
	private ArrayList<AppData> AppData;
	private String Description;
	private Date EndTime;
	private String EventType;
	private String ID;
	private String ImageURL;
	private Date LastUpdate;
	private int MaxParticipants;
	private int MinParticipants;
	private String EventName;
	private String Notes;
	private String PersonID;
	private String PersonName;
	private EventPrivacy Privacy;
	private Date StartTime;
	private EventStatus Status;
	private String LocationID;
	private double Latitude;
	private double Longtitude;
	private String Address1;
	private String Address2;
	private String City;
	private String State;
	private String Zip;
	private String CountryName;
	private String CountryCode;

	public ArrayList<AppData> getAppData() {
		return AppData;
	}

	public void setAppData(ArrayList<AppData> appData) {
		AppData = appData;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Date getEndTime() {
		return EndTime;
	}

	public void setEndTime(Date endTime) {
		EndTime = endTime;
	}

	public String getEventType() {
		return EventType;
	}

	public void setEventType(String eventType) {
		EventType = eventType;
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

	public Date getLastUpdate() {
		return LastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		LastUpdate = lastUpdate;
	}

	public int getMaxParticipants() {
		return MaxParticipants;
	}

	public void setMaxParticipants(int maxParticipants) {
		MaxParticipants = maxParticipants;
	}

	public int getMinParticipants() {
		return MinParticipants;
	}

	public void setMinParticipants(int minParticipants) {
		MinParticipants = minParticipants;
	}

	public String getEventName() {
		return EventName;
	}

	public void setEventName(String eventName) {
		EventName = eventName;
	}

	public String getNotes() {
		return Notes;
	}

	public void setNotes(String notes) {
		Notes = notes;
	}

	public String getPersonID() {
		return PersonID;
	}

	public void setPersonID(String personID) {
		PersonID = personID;
	}

	public String getPersonName() {
		return PersonName;
	}

	public void setPersonName(String personName) {
		PersonName = personName;
	}

	public EventPrivacy getPrivacy() {
		return Privacy;
	}

	public void setPrivacy(EventPrivacy privacy) {
		Privacy = privacy;
	}

	public Date getStartTime() {
		return StartTime;
	}

	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}

	public EventStatus getStatus() {
		return Status;
	}

	public void setStatus(EventStatus status) {
		Status = status;
	}

	public String getLocationID() {
		return LocationID;
	}

	public void setLocationID(String locationID) {
		LocationID = locationID;
	}

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

	public double getLongtitude() {
		return Longtitude;
	}

	public void setLongtitude(double longtitude) {
		Longtitude = longtitude;
	}

	public String getAddress1() {
		return Address1;
	}

	public void setAddress1(String address1) {
		Address1 = address1;
	}

	public String getAddress2() {
		return Address2;
	}

	public void setAddress2(String address2) {
		Address2 = address2;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getZip() {
		return Zip;
	}

	public void setZip(String zip) {
		Zip = zip;
	}

	public String getCountryName() {
		return CountryName;
	}

	public void setCountryName(String countryName) {
		CountryName = countryName;
	}

	public String getCountryCode() {
		return CountryCode;
	}

	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}

}
