package com.proxomoandroidsdk.api;

import java.net.URLEncoder;
import java.util.ArrayList;

import com.proxomoandroidsdk.definitions.ContinuationToken;
import com.proxomoandroidsdk.definitions.Token;
import com.proxomoandroidsdk.enums.Enums.CommunicationType;
import com.proxomoandroidsdk.helpers.ProxomoWebRequest;
import com.proxomoandroidsdk.test.MyCustomData;

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
		return this._appID;
	}

	public String getProxomoAPIKey() {
		return this._proxomoAPIkey;
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

	public ProxomoApi(String _apiVersion, Token _authToken,
			CommunicationType _format) {

		this._apiVersion = _apiVersion;
		this._authToken = _authToken;
		this._format = _format;
	}

	private void init(String appId, String proxomoApiKey, String version,
			CommunicationType format, String url, Token t) {
		this._apiVersion = version;
		this._appID = appId;
		this._proxomoAPIkey = proxomoApiKey;

		if (format == CommunicationType.XML) {
			contentType = "text/xml";
			if (url == null && url == "") {
				baseURL = String.format("https://service.proxomo.comm/{0}/xml",
						_apiVersion);

			} else {
				baseURL = String.format("{0}/{1}/xml", url, _apiVersion);
			}
		} else if (format == CommunicationType.JSON) {
			contentType = "text/json";
			if (url == null && url == "") {
				baseURL = String.format(
						"https://service.proxomo.comm/{0}/json", _apiVersion);

			} else {
				baseURL = String.format("{0}/{1}/json", url, _apiVersion);
			}
		}
	}

	private void getAuthToken() {
		String url = String
				.format("{0}/security/accesstoken/get?applicationid={1}&proxomoAPIkey={2}",
						baseURL, URLEncoder.encode(_appID),
						URLEncoder.encode(_proxomoAPIkey));
		ProxomoWebRequest p = new ProxomoWebRequest(_format);
		p.getData(url, "GET", "");

	}

	public <T> ArrayList<T> CustomDataSearch(String tableName, String query,
			int maxResults, ContinuationToken ctokens) {

		return null;

	}
}
