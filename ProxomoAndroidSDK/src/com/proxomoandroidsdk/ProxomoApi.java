package com.proxomoandroidsdk;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;

import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.proxomoandroidsdk.definitions.AppData;
import com.proxomoandroidsdk.definitions.Category;
import com.proxomoandroidsdk.definitions.ContinuationToken;
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
import com.proxomoandroidsdk.definitions.SocialNetworkFriend;
import com.proxomoandroidsdk.definitions.Token;
import com.proxomoandroidsdk.definitions.UserToken;
import com.proxomoandroidsdk.enums.Enums.CommunicationType;
import com.proxomoandroidsdk.enums.Enums.EventParticipantStatus;
import com.proxomoandroidsdk.enums.Enums.FriendResponse;
import com.proxomoandroidsdk.enums.Enums.LocationSearchScope;
import com.proxomoandroidsdk.enums.Enums.SocialNetwork;
import com.proxomoandroidsdk.helpers.JSONParser;
import com.proxomoandroidsdk.helpers.Utility;

public class ProxomoApi {
	private static String baseURL = "";
	private static String _proxomoAPIkey = "";
	private static String _appID = "";
	private String contentType = "application/json";
	private String _apiVersion = "v09";
	private Token _authToken = new Token();
	private CommunicationType _format = CommunicationType.JSON;

	public CommunicationType get_format() {
		return _format;
	}

	public void set_format(CommunicationType _format) {
		this._format = _format;
	}

	public String getAppId() {
		return ProxomoApi._appID;
	}

	public String getProxomoAPIKey() {
		return ProxomoApi._proxomoAPIkey;
	}

	public String getApiVersion() {
		return this._apiVersion;
	}

	public void setApiVersion(String ver) {
		this._apiVersion = ver;
	}

	public Token get_authToken() {
		return _authToken;
	}

	public void set_authToken(Token _authToken) {
		this._authToken = _authToken;
	}

	public ProxomoApi(String _appId, String _proxomoApiKey, Token _authToken,
			CommunicationType _format, String url) {
		_format = CommunicationType.JSON;
		if (url == null)
			url = "";
		_authToken = null;
		init(_appId, _proxomoApiKey, "v09", _format, url, _authToken);
	}

	public ProxomoApi(String appId, String apiKey, String version,
			CommunicationType format, String url, Token t) {
		_format = CommunicationType.JSON;
		if (url == null)
			url = "";
		_authToken = null;
		init(appId, apiKey, version, _format, url, _authToken);
	}

	private void init(String appId, String proxomoApiKey, String version,
			CommunicationType format, String url, Token t) {
		this._apiVersion = version;
		ProxomoApi._appID = appId;
		ProxomoApi._proxomoAPIkey = proxomoApiKey;
		switch (format) {
		case XML:
			contentType = "text/xml";
			if (url == null || url == "") {
				Log.i("debug", _apiVersion);
				baseURL = String.format("https://service.proxomo.com/%1$s/xml",
						_apiVersion);
				System.out.print(baseURL);
			} else {
				baseURL = String.format("%1$s/%2$s/xml", url, _apiVersion);
			}
			break;
		default:
			contentType = "text/json";
			if (url == null || url == "") {

				baseURL = String.format(
						"https://service.proxomo.com/%1$s/json", _apiVersion);
				Log.i("debug", baseURL);
			} else {
				baseURL = String.format("%1$s/%2$s/json", url, _apiVersion);
			}
			break;
		}
		if (t != null) {
			if (t.getExpireDate().before(new Date())) {
				getAuthToken();
			} else {
				_authToken = t;
			}
		} else {
			getAuthToken();
		}
	}

	private void getAuthToken() {
		String url = String
				.format("%1$s/security/accesstoken/get?applicationid=%2$s&proxomoAPIkey=%3$s",
						baseURL, URLEncoder.encode(_appID),
						URLEncoder.encode(_proxomoAPIkey));
		ProxomoWebRequest p = new ProxomoWebRequest();
		String rawTokenData = p.getData(url, "GET", "");
		rawTokenData = JSONParser.fixExpireDate(rawTokenData);
		_authToken = JSONParser.getInstance().fromJson(rawTokenData,
				Token.class);
	}

	// ========================= CUSTOM DATA =================================
	public Object CustomDataSearch(String tableName, String query,
			int maxResults, ContinuationToken ctokens, Type t) {
		String url = String.format(
				"%1$s/customdata/search/table/%2$s?q=%3$s&maxresults=%4$s",
				baseURL, tableName, query, maxResults);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String rawJsonData = p.getData(url, "GET", contentType, "", ctokens);
		return JSONParser.getInstance().fromJson(rawJsonData, t);
	}

	public String CustomDataAdd(Object o) {
		String url = String.format("%1$s/customdata", baseURL);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String json = JSONParser.getInstance().toJson(o);
		String returnJson = p.getData(url, "POST", contentType, json);
		return JSONParser.getInstance().fromJson(returnJson, String.class);
	}

	public void CustomDataDelete(String tblName, String id) {
		String url = String.format("%1$s/customdata/table/%2$s/%3$s", baseURL,
				tblName, id);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "DELETE", contentType);
	}

	public String CustomDataUpdate(Object o) {
		String url = String.format("%1$s/customdata", baseURL);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String json = JSONParser.getInstance().toJson(o);
		return p.getData(url, "PUT", contentType, json);
	}

	public Object CustomDataGet(String tblName, String id, Type t) {
		String url = String.format("%1$s/customdata/table/%2$s/%3$s", baseURL,
				tblName, id);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String rawJson = p.getData(url, "GET", contentType);
		return JSONParser.getInstance().fromJson(rawJson, t);
	}

	// ====================== APP DATA ===================================
	public String appDataAdd(AppData app) {
		String url = String.format("%1$s/appdata", baseURL);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String json = JSONParser.getInstance().toJson(app);
		String rawJson = p.getData(url, "POST", contentType, json);
		return JSONParser.getInstance().fromJson(rawJson, String.class);
	}

	public void appDataDelete(String id) {
		String url = String.format("%1$s/appdata/%2$s", baseURL, id);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "DELETE", contentType);
	}

	public AppData appDataGet(String id) {
		String url = String.format("%1$s/appdata/%2$s", baseURL, id);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String rawjson = p.getData(url, "GET", contentType);
		return JSONParser.getInstance().fromJson(rawjson, AppData.class);
	}

	public void appDataUpdate(AppData app) {
		String url = String.format("%1$s/appdata", baseURL);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String json = JSONParser.getInstance().toJson(app);
		p.getData(url, "PUT", contentType, json);
	}

	public ArrayList<AppData> appDataGetAll() {
		String url = String.format("%1$s/appdata", baseURL);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String rawJson = p.getData(url, "GET", contentType);
		Type t = new TypeToken<ArrayList<AppData>>() {
		}.getType();
		return JSONParser.getInstance().fromJson(rawJson, t);
	}

	public ArrayList<AppData> appDataSearch(String objectType) {
		String url = String.format("%1$s/appdata/search/objecttype/%2$s",
				baseURL, objectType);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String json = p.getData(url, "GET", contentType);
		Type t = new TypeToken<ArrayList<AppData>>() {
		}.getType();
		return JSONParser.getInstance().fromJson(json, t);
	}

	// ========================= EVENT ==================================

	public String eventAdd(Event e) {
		String url = String.format("%1$s/event", baseURL);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String json = JSONParser.getInstance().toJson(e);
		return JSONParser.getInstance().fromJson(
				p.getData(url, "POST", contentType, json), String.class);
	}

	public Event eventGet(String id) {
		String url = String.format("%1$s/event/%2$s", baseURL, id);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String json = p.getData(url, "GET", contentType);
		return JSONParser.getInstance().fromJson(json, Event.class);
	}

	public void eventUpdate(Event e) {
		String url = String.format("%1$s/event", baseURL);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String json = JSONParser.getInstance().toJson(e);
		p.getData(url, "PUT", contentType, json);
	}

	public ArrayList<Event> eventsGetByDistance(int lat, int lng, int distance,
			Date startTime, Date endTime, String eventType) {
		String url = null;
		if (eventType == null || eventType == "") {
			url = String
					.format("%1$s/events/search/latitude/%2$s/longitude/%3$s/distance/%4$s/start/%5$s/end/%6$s",
							baseURL, lat, lng, distance,
							Utility.convertToUnixTimeStamp(startTime),
							Utility.convertToUnixTimeStamp(endTime));
		} else {
			url = String
					.format("%1$s/events/search/latitude/%2$s/longitude/%3$s/distance/%4$s/start/%5$s/end/%6$s?eventtype=%7$s",
							baseURL, lat, lng, distance,
							Utility.convertToUnixTimeStamp(startTime),
							Utility.convertToUnixTimeStamp(endTime), eventType);
		}
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String json = p.getData(url, "GET", contentType);
		Type t = new TypeToken<ArrayList<Event>>() {
		}.getType();
		return JSONParser.getInstance().fromJson(json, t);
	}

	public ArrayList<Event> eventsGetByPerson(String personID, Date start,
			Date end, String eventType) {
		String url = null;
		if (eventType == null || eventType == "")
			url = String.format(
					"%1$s/events/search/personid/%2$s/start/%3$s/end/%4$s",
					baseURL, personID, Utility.convertToUnixTimeStamp(start),
					Utility.convertToUnixTimeStamp(end));
		else
			url = String
					.format("%1$s/events/search/personid/%2$s/start/%3$s/end/%4$s?eventtype=%5$s",
							baseURL, personID,
							Utility.convertToUnixTimeStamp(start),
							Utility.convertToUnixTimeStamp(end), eventType);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String json = p.getData(url, "GET", contentType);
		Type t = new TypeToken<ArrayList<Event>>() {
		}.getType();
		return JSONParser.getInstance().fromJson(json, t);
	}

	// ======================= EVENT COMMENT ==============================
	public String eventCommentAdd(String eventId, EventComment ec) {
		String url = String.format("%1$s/event/%2$s/comment", baseURL, eventId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String json = JSONParser.getInstance().toJson(ec);
		String returnedJson = p.getData(url, "POST", contentType, json);
		return JSONParser.getInstance().fromJson(returnedJson, String.class);
	}

	public ArrayList<EventComment> eventCommentsGet(String eventID) {
		String url = String
				.format("%1$s/event/%2$s/comments", baseURL, eventID);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String returnedJson = p.getData(url, "GET", contentType);
		Type t = new TypeToken<ArrayList<EventComment>>() {
		}.getType();
		return JSONParser.getInstance().fromJson(returnedJson, t);
	}

	public void eventCommentUpdate(String eventid, EventComment ec) {
		String url = String.format("%1$s/event/%2$s/comment", baseURL, eventid);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String json = JSONParser.getInstance().toJson(ec);
		p.getData(url, "PUT", contentType, json);
	}

	public void eventCommentDelete(String eventId, String commentId) {
		String url = String.format("%1$s/event/%2$s/comment/%3$s", baseURL,
				eventId, commentId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "DELETE", contentType);
	}

	// ======================= EVENT PARTICIPANTS PART ======================
	public void eventParticipantInvite(String eventId, String personId) {
		String url = String.format(
				"%1$s/event/%2$s/participant/invite/personid/%3$s", baseURL,
				eventId, personId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "PUT", contentType);
	}

	public void eventParticipantsInvite(String eventId, String[] personIds) {
		String personIdStr = "";
		for (int i = 0; i < personIds.length; i++) {
			personIdStr += personIds[i];
			if (i != personIds.length - 1) {
				personIdStr += ",";
			}
		}
		String url = String.format(
				"%1$s/event/%2$s/participants/invite/personids/%3$s", baseURL,
				eventId, personIdStr);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "PUT", contentType);
	}

	public ArrayList<EventParticipant> eventParticipantsGet(String eventId) {
		String url = String.format("%1$s/event/%2$s/participants", baseURL,
				eventId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		Type t = new TypeToken<ArrayList<EventParticipant>>() {
		}.getType();
		return JSONParser.getInstance().fromJson(
				p.getData(url, "GET", contentType), t);
	}

	public void eventParticipantDelete(String eventId, String participantId) {
		String url = String.format("%1$s/event/%2$s/participant/%3$s", baseURL,
				eventId, participantId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "DELETE", contentType);
	}

	public void eventRequestInvitation(String eventId, String personId) {
		String url = String.format(
				"%1$s/event/%2$s/requestinvite/personid/%3$s", baseURL,
				eventId, personId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "PUT", contentType);
	}

	public void eventRSVP(String eventId,
			EventParticipantStatus participantStatus, String personId) {
		String url = String.format(
				"%1$s/event/%2$s/rsvp/personid/%3$s/participantstatus/%4$s",
				baseURL, eventId, personId, participantStatus.convert());
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "PUT", contentType);

	}

	// ======================= EVENT APPDATA PART ===========================

	public String eventAppDataAdd(String eventId, AppData ad) {
		String url = String.format("%1$s/event/%2$s/appdata", baseURL, eventId);
		String json = JSONParser.getInstance().toJson(ad);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		return JSONParser.getInstance().fromJson(
				p.getData(url, "POST", contentType, json), String.class);
	}

	public AppData eventAppDataGet(String eventId, String appDataId) {
		String url = String.format("%1$s/event/%2$s/appdata/%3$s", baseURL,
				eventId, appDataId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		return JSONParser.getInstance().fromJson(
				p.getData(url, "GET", contentType), AppData.class);
	}

	public ArrayList<AppData> eventAppDataGetAll(String eventId) {
		String url = String.format("%1$s/event/%2$s/appdata", baseURL, eventId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		Type t = new TypeToken<ArrayList<AppData>>() {
		}.getType();
		return JSONParser.getInstance().fromJson(
				p.getData(url, "GET", contentType), t);
	}

	public void eventAppDataUpdate(String eventId, AppData ad) {
		String url = String.format("%1$s/event/%2$s/appdata", baseURL, eventId);
		String json = JSONParser.getInstance().toJson(ad);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "PUT", contentType, json);
	}

	public void eventAppDataDelete(String eventId, String appDataId) {
		String url = String.format("%1$s/event/%2$s/appdata/%3$s", baseURL,
				eventId, appDataId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "DELETE", contentType);
	}

	// ======================= FRIEND =======================================
	public void friendInvite(String friendA, String friendB) {
		String url = String.format(
				"%1$s/friend/invite/frienda/%2$s/friendb/%3$s", baseURL,
				friendA, friendB);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "PUT", contentType);
	}

	public ArrayList<Friend> friendsGet(String personID) {
		String url = String.format("%1$s/friends/personid/%2$s", baseURL,
				personID);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		Type t = new TypeToken<ArrayList<Friend>>() {
		}.getType();
		return JSONParser.getInstance().fromJson(
				p.getData(url, "GET", contentType), t);
	}

	public void friendBySocialNetworkInvite(SocialNetwork socialNetwork,
			String frienda, String friendb) {
		String url = String
				.format("%1$s/friend/invite/frienda/%2$s/friendb/%3$s/socialnetwork/%4$s",
						baseURL, frienda, friendb, socialNetwork.convert());
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "PUT", contentType);
	}

	public void friendshipRespond(FriendResponse response, String frienda,
			String friendb) {
		String url = String
				.format("%1$s/friend/respond/frienda/%2$s/friendb/%3$s/friendresponse/%4$s",
						baseURL, frienda, friendb, response.convert());
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "PUT", contentType);
	}

	public ArrayList<SocialNetworkFriend> friendSocialNetworkGet(
			SocialNetwork socialNetwork, String personID) {
		String url = String.format(
				"%1$s/friends/personid/%2$s/socialnetwork/%3$s", baseURL,
				personID, socialNetwork.convert());
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		Type t = new TypeToken<ArrayList<SocialNetworkFriend>>() {
		}.getType();
		return JSONParser.getInstance().fromJson(
				p.getData(url, "GET", contentType), t);
	}

	// ======================= GEO CODE =====================================
	public GeoCode geoCodeByAddress(String address) {
		String url = String.format("%1$s/geo/lookup/address/%2$s", baseURL,
				address);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		return JSONParser.getInstance().fromJson(
				p.getData(url, "GET", contentType), GeoCode.class);
	}

	public Location reverseGeoCode(String latitue, String longitude) {
		String url = String.format(
				"%1$s/geo/lookup/latitude/%2$s/longitude/%3$s", baseURL,
				latitue, longitude);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		return JSONParser.getInstance().fromJson(
				p.getData(url, "GET", contentType), Location.class);
	}

	public GeoIP geoCodeByIPAddress(String ipAddress) {
		String url = String.format("%1$s/geo/lookup/ip/%2$s", baseURL,
				ipAddress);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		return JSONParser.getInstance().fromJson(
				p.getData(url, "GET", contentType), GeoIP.class);
	}

	// ======================= LOCATION =====================================
	public String locationAdd(Location l) {
		String url = String.format("%1$s/location", baseURL);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String json = JSONParser.getInstance().toJson(l);
		return JSONParser.getInstance().fromJson(
				p.getData(url, "POST", contentType, json), String.class);
	}

	public Location locationGet(String id) {
		String url = String.format("%1$s/location/%2$s", baseURL, id);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		return JSONParser.getInstance().fromJson(
				p.getData(url, "GET", contentType), Location.class);
	}

	public void locationUpdate(Location l) {
		String url = String.format("%1$s/location", baseURL);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "PUT", contentType, JSONParser.getInstance().toJson(l));
	}

	public void locationDelete(String id) {
		String url = String.format("%1$s/location/%2$s", baseURL, id);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "DELETE", contentType);
	}

	public ArrayList<Category> locationCategoriesGet() {
		String url = String.format("%1$s/location/categories", baseURL);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		Type t = new TypeToken<ArrayList<Category>>() {
		}.getType();
		return JSONParser.getInstance().fromJson(
				p.getData(url, "GET", contentType), t);
	}

	public ArrayList<Location> locationSearchByAddress(String address,
			String q, String category, double radius,
			LocationSearchScope scope, int maxresults, String personId) {
		if (scope == null)
			scope = LocationSearchScope.ApplicationOnly;
		String url = String.format("%1$s/locations/search", baseURL)
				+ Utility.formatQueryString(address, "", "", q, category,
						radius, scope, maxresults, personId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		Type t = new TypeToken<ArrayList<Location>>() {
		}.getType();
		String returnedJson = p.getData(url, "GET", contentType);
		return JSONParser.getInstance().fromJson(returnedJson, t);
	}

	/**
	 * locationType param need to be not empty string otherwise will return 403
	 * not found error
	 * 
	 * @param locationType
	 * @return
	 */
	public ArrayList<Location> locationSearchByLocationType(String locationType) {
		String url = String.format("%1$s/locations/search/locationtype/%2$s",
				baseURL, locationType);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		Type t = new TypeToken<ArrayList<Location>>() {
		}.getType();
		return JSONParser.getInstance().fromJson(
				p.getData(url, "GET", contentType), t);
	}

	public ArrayList<Location> locationSearchByGPS(String latitude,
			String longitude, String q, String category, double radius,
			LocationSearchScope scope, int maxresults, String personId) {
		if (scope == null)
			scope = LocationSearchScope.ApplicationOnly;
		String url = String.format(
				"%1$s/locations/search/latitude/%2$s/longitude/%3$s", baseURL,
				latitude, longitude)
				+ Utility.formatQueryString("", "", "", q, category, radius,
						scope, maxresults, personId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		Type t = new TypeToken<ArrayList<Location>>() {
		}.getType();
		return JSONParser.getInstance().fromJson(
				p.getData(url, "GET", contentType), t);
	}

	public ArrayList<Location> locationSearchByIPAddress(String ipAddress,
			String q, String category, double radius,
			LocationSearchScope scope, int maxresults, String personId) {
		if (scope == null)
			scope = LocationSearchScope.ApplicationOnly;
		String url = String.format("%1$s/locations/search/ip/%2$s", baseURL,
				ipAddress)
				+ Utility.formatQueryString("", "", "", q, category, radius,
						scope, maxresults, personId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		Type t = new TypeToken<ArrayList<Location>>() {
		}.getType();
		return JSONParser.getInstance().fromJson(
				p.getData(url, "GET", contentType), t);
	}

	// ======================= LOCATION APPDATA =============================
	public String locationAppDataAdd(String locationId, AppData ad) {
		String url = String.format("%1$s/location/%2$s/appdata", baseURL,
				locationId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		return JSONParser.getInstance().fromJson(
				p.getData(url, "POST", contentType, JSONParser.getInstance()
						.toJson(ad)), String.class);
	}

	public void locationAppDataDelete(String locationid, String appdataId) {
		String url = String.format("%1$s/location/%2$s/appdata/%3$s", baseURL,
				locationid, appdataId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "DELETE", contentType);
	}

	public AppData locationAppDataGet(String locationId, String appDataId) {
		String url = String.format("%1$s/location/%2$s/appdata/%3$s", baseURL,
				locationId, appDataId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String returnedJson=p.getData(url, "GET", contentType);
		return JSONParser.getInstance().fromJson(
				returnedJson, AppData.class);
	}

	public ArrayList<AppData> locationAppDataGetAll(String locationID) {
		String url = String.format("%1$s/location/%2$s/appdata", baseURL,
				locationID);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		Type t = new TypeToken<ArrayList<AppData>>() {
		}.getType();
		return JSONParser.getInstance().fromJson(
				p.getData(url, "GET", contentType), t);
	}

	public void locationAppDataUpdate(String locationId, AppData ad) {
		String url = String.format("%1$s/location/%2$s/appdata", baseURL,
				locationId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "PUT", contentType, JSONParser.getInstance().toJson(ad));
	}

	// ======================= NOTIFICATION =================================
	public void notificationSend(Notification n) {
		String url = String.format("%1$s/notification", baseURL);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "POST", contentType, JSONParser.getInstance().toJson(n));
	}

	// ======================= SECURITY PART ================================
	public PersonLogin securityPersonCreate(String username, String pass,
			String role) {
		String url = String
				.format("%1$s/security/person/create?username=%2$s&password=%3$s&role=%4$s",
						baseURL, username, pass, role);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String returnendJson = p.getData(url, "POST", contentType);
		return JSONParser.getInstance().fromJson(returnendJson,
				PersonLogin.class);
	}

	public void securityPersonDelete(String personID) {
		String url = String.format("%1$s/security/person/%2$s", baseURL,
				personID);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "DELETE", contentType);
	}

	public ArrayList<PersonLogin> securityPersonGetAll() {
		String url = String.format("%1$s/security/persons", baseURL);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String returnJson = p.getData(url, "GET", contentType);
		Type t = new TypeToken<ArrayList<PersonLogin>>() {
		}.getType();
		return JSONParser.getInstance().fromJson(returnJson, t);
	}

	public UserToken securityPersonAuthenticate(String username, String pass) {
		String url = String
				.format("%1$s/security/person/authenticate?applicationid=%2$s&username=%3$s&password=%4$s",
						baseURL, _appID, username, pass);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String returnedJson = p.getData(url, "GET", contentType);
		UserToken ut = JSONParser.getInstance().fromJson(returnedJson,
				UserToken.class);
		return ut;
	}

	public String securityPersonPasswordChangeRequest(String username) {
		String url = String.format(
				"%1$s/security/person/passwordchange/request/%2$s", baseURL,
				username);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String returnedJson = p.getData(url, "GET", contentType);
		return JSONParser.getInstance().fromJson(returnedJson, String.class);
	}

	public PersonLogin securityPersonPasswordChange(String username,
			String password, String resetToken) {
		String url = String
				.format("%1$s/security/person/passwordchange?username=%2$s&password=%3$s&resettoken=%4$s",
						baseURL, username, password, resetToken);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		String returnedJson = p.getData(url, "GET", contentType);
		return JSONParser.getInstance().fromJson(returnedJson,
				PersonLogin.class);
	}

	public void securityPersonUpdateRole(String personID, String role) {
		String url = String.format(
				"%1$s/security/person/update/role?personid=%2$s&role=%3$s",
				baseURL, personID, role);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "PUT", contentType);
	}

	// ============================== PERSON PART ==============================
	public Person personGet(String id) {
		String url = String.format("%1$s/person/%2$s", baseURL, id);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		return JSONParser.getInstance().fromJson(
				p.getData(url, "GET", contentType), Person.class);
	}

	public void personUpdate(Person person) {
		String url = String.format("%1$s/person", baseURL);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "PUT", contentType,
				JSONParser.getInstance().toJson(person));
	}

	public String personAppDataAdd(String personId, AppData ad) {
		String url = String.format("%1$s/person/%2$s/appdata", baseURL,
				personId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		return JSONParser.getInstance().fromJson(
				p.getData(url, "POST", contentType, JSONParser.getInstance()
						.toJson(ad)), String.class);
	}

	public AppData personAppDataGet(String personId, String appId) {
		String url = String.format("%1$s/person/%2$s/appdata/%3$s", baseURL,
				personId, appId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		return JSONParser.getInstance().fromJson(
				p.getData(url, "GET", contentType), AppData.class);
	}

	public ArrayList<AppData> personAppDataGetAll(String personId) {
		String url = String.format("%1$s/person/%2$s/appdata", baseURL,
				personId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		Type t = new TypeToken<ArrayList<AppData>>() {
		}.getType();
		return JSONParser.getInstance().fromJson(
				p.getData(url, "GET", contentType), t);
	}

	public void personAppDataDelete(String personId, String appDataId) {
		String url = String.format("%1$s/person/%2$s/appdata/%3$s", baseURL,
				personId, appDataId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "DELETE", contentType);
	}

	public void personAppDataUpdate(String personId, AppData ad) {
		String url = String.format("%1$s/person/%2$s/appdata", baseURL,
				personId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		p.getData(url, "PUT", contentType, JSONParser.getInstance().toJson(ad));
	}

	public ArrayList<Location> personLocationsGet(String personId, String lat,
			String lng, double radius, int maxresults) {
		String url = String.format("%1$s/person/%2$s/locations", baseURL,
				personId)
				+ Utility.formatQueryString("", lat, lng, "", "", radius,
						LocationSearchScope.ApplicationOnly, maxresults,
						personId);
		ProxomoWebRequest p = new ProxomoWebRequest(
				_authToken.getAccessToken());
		Type t = new TypeToken<ArrayList<Location>>() {
		}.getType();
		return JSONParser.getInstance().fromJson(
				p.getData(url, "GET", contentType), t);
	}

	// ============================== TASK PART ================================
	// (need to be taken out)
	// public String taskAdd(Task t) {
	// String url = String.format("%1$s/task", baseURL);
	// ProxomoWebRequest p = new ProxomoWebRequest(
	// _authToken.getAccessToken(), _format);
	// String json = JSONParser.getInstance().toJson(t);
	// String returnedJson = p.getData(url, "POST", contentType, json);
	// return JSONParser.getInstance().fromJson(returnedJson, String.class);
	// }
	//
	// public void taskDelete(String id) {
	// String url = String.format("%1$s/task/%2$s", baseURL, id);
	// ProxomoWebRequest p = new ProxomoWebRequest(
	// _authToken.getAccessToken(), _format);
	// p.getData(url, "DELETE", contentType);
	// }
	//
	// public Task taskGet(String id) {
	// String url = String.format("%1$s/task/%2$s", baseURL, id);
	// ProxomoWebRequest p = new ProxomoWebRequest(
	// _authToken.getAccessToken(), _format);
	// return JSONParser.getInstance().fromJson(
	// p.getData(url, "GET", contentType), Task.class);
	// }
	//
	// public void taskUpdate(Task t) {
	// String url = String.format("%1$s/task", baseURL);
	// ProxomoWebRequest p = new ProxomoWebRequest(
	// _authToken.getAccessToken(), _format);
	// p.getData(url, "PUT", contentType, JSONParser.getInstance().toJson(t));
	// }
	//
	// public ArrayList<Task> taskGetAll() {
	// String url = String.format("%1$s/tasks", baseURL);
	// ProxomoWebRequest p = new ProxomoWebRequest(
	// _authToken.getAccessToken(), _format);
	// Type t = new TypeToken<ArrayList<Task>>() {
	// }.getType();
	// return JSONParser.getInstance().fromJson(
	// p.getData(url, "GET", contentType), t);
	// }
	//
	// public ArrayList<Task> taskGetByPersonId(String personID) {
	// String url = String.format("%1$s/tasks/person/%2$s", baseURL, personID);
	// ProxomoWebRequest p = new ProxomoWebRequest(
	// _authToken.getAccessToken(), _format);
	// Type t = new TypeToken<ArrayList<Task>>() {
	// }.getType();
	// return JSONParser.getInstance().fromJson(
	// p.getData(url, "GET", contentType), t);
	// }
}
