package com.proxomoandroidsdk.definitions;

import java.util.ArrayList;

import com.proxomoandroidsdk.enums.Enums.LocationSecurity;

public class Location {
	private String ID;
	private ArrayList<AppData> AppData;
	private String Name;
	private double Distance;
	private Category Category;
	private double Latitude;
	private double Longitude;
	private String Address1;
	private String Address2;
	private String City;
	private String State;
	private String Zip;
	private String CountryName;
	private String CountryCode;
	private String LocationType;
	private String TimeZoneName;
	private LocationSecurity LocationSecurity;
	private String PersonID;
	private double UTCOffset;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public ArrayList<AppData> getAppData() {
		return AppData;
	}

	public void setAppData(ArrayList<AppData> appData) {
		AppData = appData;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public double getDistance() {
		return Distance;
	}

	public void setDistance(double distance) {
		Distance = distance;
	}

	public Category getCategory() {
		return Category;
	}

	public void setCategory(Category category) {
		Category = category;
	}

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double longtitude) {
		Longitude = longtitude;
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

	public String getLocationType() {
		return LocationType;
	}

	public void setLocationType(String locationType) {
		LocationType = locationType;
	}

	public String getTimeZoneName() {
		return TimeZoneName;
	}

	public void setTimeZoneName(String timeZoneName) {
		TimeZoneName = timeZoneName;
	}

	public LocationSecurity getLocationSecurity() {
		return LocationSecurity;
	}

	public void setLocationSecurity(LocationSecurity locationSecurity) {
		LocationSecurity = locationSecurity;
	}

	public String getPersonID() {
		return PersonID;
	}

	public void setPersonID(String personID) {
		PersonID = personID;
	}

	public double getUTCOffset() {
		return UTCOffset;
	}

	public void setUTCOffset(double uTCOffset) {
		UTCOffset = uTCOffset;
	}

}
