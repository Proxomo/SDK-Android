package com.myapp;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.google.gson.reflect.TypeToken;
import com.proxomoandroidsdk.ProxomoApi;
import com.proxomoandroidsdk.definitions.AppData;
import com.proxomoandroidsdk.definitions.Category;
import com.proxomoandroidsdk.definitions.Event;
import com.proxomoandroidsdk.definitions.EventComment;
import com.proxomoandroidsdk.definitions.EventParticipant;
import com.proxomoandroidsdk.definitions.Friend;
import com.proxomoandroidsdk.definitions.GeoCode;
import com.proxomoandroidsdk.definitions.GeoIP;
import com.proxomoandroidsdk.definitions.Location;
import com.proxomoandroidsdk.definitions.Notification;
import com.proxomoandroidsdk.definitions.Person;
import com.proxomoandroidsdk.definitions.PersonLogin;
import com.proxomoandroidsdk.definitions.UserToken;
import com.proxomoandroidsdk.enums.Enums.CommunicationType;
import com.proxomoandroidsdk.enums.Enums.EventParticipantStatus;
import com.proxomoandroidsdk.enums.Enums.EventPrivacy;
import com.proxomoandroidsdk.enums.Enums.EventStatus;
import com.proxomoandroidsdk.enums.Enums.FriendResponse;
import com.proxomoandroidsdk.enums.Enums.LocationSearchScope;
import com.proxomoandroidsdk.enums.Enums.NotificationSendMethod;
import com.proxomoandroidsdk.enums.Enums.NotificationType;
import com.proxomoandroidsdk.enums.Enums.VerificationStatus;
import com.proxomoandroidsdk.helpers.JSONParser;
import com.proxomoandroidsdk.test.MyCustomData;

public class MainActivity extends Activity {

	String appId = "HerDPUfmteiNdi6Z";
	String apiKey = "GMRqmIeLaqGkDFn6tw4wEQuqsje2GEFa6+DKbLsJdLk=";
	ProxomoApi api = new ProxomoApi(appId, apiKey, null,
			CommunicationType.JSON, "https://sandbox.proxomo.com");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		JSONParser.registerEnumToFactory();
		 this.testCustomDataStorage();
		 this.testAppData();
		 this.testEvent();
		 this.testEventComment();
		 this.testEventAppData();
		 this.testEventParticipant();

		 this.testSecurity();

		 this.testGeoLocation();
//		 this.testLocation();
		this.testPerson();
		 this.testFriend();
//		 this.testNotification();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void testCustomDataStorage() {
		Type t = new TypeToken<ArrayList<MyCustomData>>() {
		}.getType();
		Type t1 = new TypeToken<MyCustomData>() {
		}.getType();
		// test add custom data part
		MyCustomData m = new MyCustomData();
		m.setTableName("MyCustomTable");
		m.setData("data 5");
		m.setDate(new Date());

		String id = api.CustomDataAdd(m);
		Log.i("debug custom data add", id);

		// test get single data
		MyCustomData mcd = (MyCustomData) api.CustomDataGet("MyCustomTable",
				id, t1);
		Log.i("debug customdataget", m.getData());

		// test update single data
		mcd.setData("this is new data");
		api.CustomDataUpdate(mcd);

		// test get all data
		@SuppressWarnings("unchecked")
		ArrayList<MyCustomData> al = (ArrayList<MyCustomData>) api
				.CustomDataSearch("MyCustomTable", "", 20, null, t);
		Log.i("debug get all data", al.get(0).getData());

		// test delete data
		api.CustomDataDelete("MyCustomTable", id);
		try {
			mcd = (MyCustomData) api.CustomDataGet("MyCustomTable", id, t1);
			Log.i("debug customdataget", mcd.getData());
		} catch (Exception e) {
			Log.i("debug custom data delete", "success");
		}
	}

	public void testAppData() {
		// test add appdata
		AppData ad = new AppData();
		ad.setKey("appkey1");
		ad.setValue("appvalue1");
		ad.setObjectType("appobjec ttype1");
		String appid = api.appDataAdd(ad);
		Log.i("debug appdata add", appid);

		ad.setValue("new update value");
		ad.setId(appid);
		api.appDataUpdate(ad);
		ad = api.appDataGet(appid);
		Log.i("debug appdata update and get", ad.getValue());

		ArrayList<AppData> al = api.appDataGetAll();
		for (int i = 0; i < al.size(); i++) {
			Log.i("debug appdatagetall", al.get(i).getId());
		}

		al = api.appDataSearch("appobjecttype1");
		for (int i = 0; i < al.size(); i++) {
			Log.i("debug appdatagetsearch", al.get(i).getId());
		}

		api.appDataDelete(appid);
		try {
			api.appDataGet(appid).getObjectType();
		} catch (Exception e) {
			Log.i("debug appdata delete", "success");
		}
	}

	public void testEvent() {
		// bugs fixed in sandbox proxomo
		// updating event and event comment are actually creating new rows

		String eventId = "u8178FTqV44ssTuv";
		String personId = "gnUhOX8KTUNNZzhn";
		// need personid for adding an event
		Event e = new Event();
		e.setAddress1("addres1");
		e.setStartTime(new Date("12/06/2012 12:00:00 pm"));
		e.setEndTime(new Date("12/06/2012 02:00:00 pm"));
		e.setLastUpdate(new Date("11/29/2012"));
		e.setCity("updated city 123");
		e.setMaxParticipants(4);
		e.setMinParticipants(1);
		e.setDescription("some description");
		e.setPrivacy(EventPrivacy.Secret);
		e.setLatitude(12);
		e.setLongtitude(12);
		e.setEventType("type");
		e.setEventName("name");
		e.setStatus(EventStatus.Upcoming);
		e.setPersonID("gnUhOX8KTUNNZzhn");
		e.setAddress2("");
		e.setCountryCode("");
		e.setImageURL("");
		e.setLocationID("");
		e.setZip("");
		String id = api.eventAdd(e);
		Log.i("Debug event add", id + "");

		e = api.eventGet(eventId);
		e.setCity("new updated city");
		api.eventUpdate(e);
		Event updatedEvent = api.eventGet(eventId);
		Log.i("debug event update", updatedEvent.getCity());

		ArrayList<Event> al = api.eventsGetByPerson(personId, new Date(
				"12/05/2012 12:00:00 pm"), new Date("12/07/2012 02:00:00 pm"),
				"");
		for (int i = 0; i < al.size(); i++) {
			Log.i("debug event get by person", al.get(i).getID());
		}

		al = api.eventsGetByDistance(12, 12, 10, new Date("12/05/2012"),
				new Date("12/07/2012"), "");
		if (al != null) {
			for (int i = 0; i < al.size(); i++) {
				Log.i("debug event get by distance", al.get(0).getID());
			}
		}
	}

	public void testEventComment() {
		String eventId = "u8178FTqV44ssTuv";
		String personId = "gnUhOX8KTUNNZzhn";
		EventComment ec = new EventComment();
		ec.setComment("some comment");
		ec.setEventID(eventId);
		ec.setPersonID(personId);
		ec.setPersonName("updated person name");
		ec.setLastUpdate(new Date());

		String ecId = api.eventCommentAdd(eventId, ec);
		Log.i("debug event comment add", ecId);
		ec.setID(ecId);
		ec.setComment("new updated comment");

		api.eventCommentUpdate(eventId, ec);
		ArrayList<EventComment> al = api.eventCommentsGet(eventId);
		Log.i("debug event comment get all", al.size() + "");

		// before delete the size (arrayList) is x, after delete the newly added
		// record the size should be x-1
		api.eventCommentDelete(eventId, ecId);
		int size = al.size();
		al = api.eventCommentsGet(eventId);
		if (size - 1 == al.size())
			Log.i("debug event comment get all", "success");
	}

	public void testEventParticipant() {
		String eventId = "u8178FTqV44ssTuv";
		String personId = "gnUhOX8KTUNNZzhn";
		String personId1 = "zm99GoAzCEopgySB";
		String personId2 = "hJpupghbesQs2ITH";
		api.eventParticipantInvite(eventId, personId);
		String[] personIDs = { personId1, personId2 };
		api.eventParticipantsInvite(eventId, personIDs);
		api.eventParticipantDelete(eventId, "neEfWXpjKRdJOcWZ");
		api.eventRequestInvitation(eventId, personId);
		api.eventRSVP(eventId, EventParticipantStatus.Maybe, personId);
		ArrayList<EventParticipant> al = api.eventParticipantsGet(eventId);
		Log.i("debug event participant get", al.get(0).getStatus() + "");

	}

	public void testEventAppData() {
		String eventId = "u8178FTqV44ssTuv";
		AppData ad = new AppData();
		ad.setKey("event ad key");
		ad.setObjectType("event ad obj type");
		ad.setValue("event updated ad value 123");
		String adId = api.eventAppDataAdd(eventId, ad);
		Log.i("debug event appdata add", adId);
		ad.setId("4KHtI36HZLyiXQtg");
		ad.setValue("new updated event appdata");
		api.eventAppDataUpdate(eventId, ad);

		ad = api.eventAppDataGet(eventId, adId);
		Log.i("debug event appdata get", ad.getValue());
		ArrayList<AppData> al = api.eventAppDataGetAll(eventId);

		Log.i("debug event appdata get all", al.size() + "");
		api.eventAppDataDelete(eventId, adId);
		int size = al.size();
		al = api.eventAppDataGetAll(eventId);
		if (size - 1 == al.size())
			Log.i("debug event appdata delete", "success");
	}

	public void testSecurity() {
		PersonLogin pl = api.securityPersonCreate("testperson2", "testperson2",
				"admin");
		try {
			Log.i("debug securityperson create", pl.getPersonID());
		} catch (Exception e) {
			Log.i("debug security person create",
					"person with specified username already existed");
		}
		UserToken ut = api.securityPersonAuthenticate("testperson2",
				"testperson2");
		Log.i("debug security person authenticate", ut.getPersonID());
		api.securityPersonUpdateRole(ut.getPersonID(), "newrole");
		ut = api.securityPersonAuthenticate("testperson2", "testperson2");
		Log.i("debug security person update role", ut.getRole());

		// api.securityPersonCreate("qwert", "qwert", "admin");
		ArrayList<PersonLogin> al = api.securityPersonGetAll();
		String token = api.securityPersonPasswordChangeRequest("testperson2");
		Log.i("debug change request", token);
		api.securityPersonPasswordChange("testperson2", "newpass", token);
		ut = api.securityPersonAuthenticate("testperson2", "newpass");
		Log.i("debug change password", ut.getPersonID()); // the role is
		// currently
		// null because this is
		// a bug for the current
		// version of the api
		// the bug is fixed on the sandbox

		api.securityPersonDelete(ut.getPersonID());
		try {
			api.securityPersonAuthenticate("testperson2", "newpass")
					.getPersonID();
		} catch (Exception e) {
			Log.i("debug delete person", "success");
		}
	}

	public void testGeoLocation() {
		GeoCode gc = api.geoCodeByAddress("772 Carousel Ln, Plano, Tx 75023");
		Log.i("debug geocode", gc.getAddress());
		GeoIP gc1 = api.geoCodeByIPAddress("76.184.188.152");
		Log.i("debug geocode by ip", gc1.getIP() + "|" + gc1.getLatitude());// need
		// ask
		// Daniel
		// why
		// null
		Location l = api.reverseGeoCode(gc.getLatitude() + "",
				gc.getLongitude() + "");
		Log.i("debug reverse geocode", l.getAddress1());

	}

	public void testLocation() {
		String personId = "gnUhOX8KTUNNZzhn";
		String locationTestID = "kAeJW6kNRWld5dku";
		Location l = new Location();
		l.setAddress1("address 1");
		l.setPersonID(personId);
		Category c = new Category();
		c.setCategory("test category");
		c.setType("test type");
		c.setSubcategory("test subcategory");
		l.setCategory(c);
		l.setLocationType("test type");
		l.setCountryCode("test code");
		l.setCountryName("test country name");
		l.setLatitude(12);
		l.setLongtitude(12);

		String id = api.locationAdd(l);
		Log.i("debug location add", id);

		api.locationDelete(id);

		l = api.locationGet(locationTestID);
		Log.i("debug location get", l.getLocationType());

		// this is not working, need find out why
		ArrayList<Category> al = api.locationCategoriesGet();
		Log.i("debug location category get", al.size() + "");

		ArrayList<Location> al1 = api.locationSearchByAddress("address 1",
				"address='address 1'", "test category", 10,
				LocationSearchScope.ApplicationOnly, 1, personId);
		Log.i("debug location search by address", al1.size() + "");// this
		// doesnt get any record, should give 1 with address "address 1"

		ArrayList<Location> al2 = api.locationSearchByLocationType("test type");
		Log.i("debug location search by type", al2.size() + "");

		AppData ad = new AppData();
		ad.setKey("test location ad key");
		ad.setValue("test location ad value");
		ad.setObjectType("test location ad obj type");
		//
		id = api.locationAppDataAdd(locationTestID, ad);
		Log.i("debug location ad add", id);
		ad = api.locationAppDataGet(locationTestID, id);
		Log.i("debug location ad get", ad.getValue());

		ad.setValue("new value");
		api.locationAppDataUpdate(locationTestID, ad);
		ad = api.locationAppDataGet(locationTestID, ad.getId());
		Log.i("debug location ad get", ad.getValue());

		api.locationAppDataDelete(locationTestID, id);
		try {
			api.locationAppDataGet(locationTestID, id).getValue();
		} catch (Exception e) {
			Log.i("debug location appdata delete", "success");
		}
		ArrayList<AppData> al11 = api.locationAppDataGetAll(locationTestID);
		Log.i("debug location appdata getall", al11.size() + "");

		ArrayList<Location> all = api.locationSearchByGPS("12", "12", "",
				"test category", 0, LocationSearchScope.ApplicationOnly, 10,
				personId);
		Log.i("debug location seach by gps", all.size() + "");
	}

	public void testPerson() {
		String personId = "gnUhOX8KTUNNZzhn";
		Person p = api.personGet(personId);
		Log.i("debug person get", p.getID());
		p.setFullName("new full name");
		api.personUpdate(p);
		p = api.personGet(personId);
		Log.i("debug person update", p.getFullName());
		ArrayList<Location> al = api.personLocationsGet(personId, "12", "12",
				0, 10);
		Log.i("debug person location get", al.size() + "");

		AppData ad = new AppData();
		ad.setKey("test person appdata ket");
		ad.setValue("test person appdata value");
		String id = api.personAppDataAdd(personId, ad);
		Log.i("debug person appdata add", id);
		ad = api.personAppDataGet(personId, id);
		ad.setObjectType("test person appdata obj type");
		api.personAppDataUpdate(personId, ad);
		ad = api.personAppDataGet(personId, id);
		Log.i("debug person appdata update", ad.getObjectType());

		ArrayList<AppData> alad = api.personAppDataGetAll(personId);
		Log.i("debug person appdata getall", alad.size() + "");
		int size = alad.size();
		api.personAppDataDelete(personId, id);
		alad = api.personAppDataGetAll(personId);
		if (size - 1 == alad.size())
			Log.i("debug person appdata delete", "success");

	}

	public void testFriend() {
		String personAID = "XVdrBHeljqsI7t7a";
		String personBID = "gnUhOX8KTUNNZzhn";
		String personCID = "tDEg1ICI8sw5TGCw";
		String personDID = "tqHjeLtEn3iJLK2d";
		Person p = api.personGet(personAID);
//		p.setFacebookID("tran.vinh.90");
//		api.personUpdate(p);
		 api.friendInvite(personBID, personDID); //1st time is incomeing, 2nd
		// is invitation sent, 3rd is approved
		 api.friendshipRespond(FriendResponse.Cancel, personBID, personDID);
//		ArrayList<SocialNetworkFriend> al1 = api.friendSocialNetworkGet(
//				SocialNetwork.Facebook, personAID);// first you have to setup a
													// Facebook application,
													// configure it to use
													// Proxomo and then
													// configure Proxomo to use
													// Facebook
//		Log.i("debug social network friend get", al1.size() + "");
		 ArrayList<Friend> al = api.friendsGet(personBID);

		 Log.i("debug friend invite", al.get(0).getFriendStatus() + "");

	}

	public void testNotification() {
		String personAID = "XVdrBHeljqsI7t7a";
		Person p = api.personGet(personAID);
		p.setEmailAddress("tranlucvinh@gmail.com");
		p.setEmailAlerts(true);
		p.setEmailVerificationStatus(VerificationStatus.Complete);
		p.setEmailVerificationCode("abc");
		p.setEmailVerified(true);
		api.personUpdate(p);
		Notification n = new Notification();
		n.setEmailMessage("test message");
		n.setEmailSubject("test subj (eclipse)");
		n.setPersonID(personAID);
		n.setNotificationType(NotificationType.EventInvite);
		n.setSendMethod(NotificationSendMethod.Email);
		api.notificationSend(n);

	}
}
