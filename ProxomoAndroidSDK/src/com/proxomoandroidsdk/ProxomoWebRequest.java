package com.proxomoandroidsdk;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import android.util.Log;

import com.proxomoandroidsdk.definitions.ContinuationToken;
import com.proxomoandroidsdk.enums.Enums.CommunicationType;

public class ProxomoWebRequest {
	public String token = "";
	private CommunicationType _format = CommunicationType.JSON;

	public void test() {
		Log.d("ok", "it works");
		System.out.println("it works");
	}

	public ProxomoWebRequest(String authToken, CommunicationType format) {
		token = authToken;
		_format = format;
	}

	public ProxomoWebRequest(CommunicationType _format) {
		this._format = _format;
	}

	String getData(String url, String method, String contentType) {
		ContinuationToken token = new ContinuationToken(null, null);
		return getData(url, method, contentType, "", token);
	}

	public String getData(String url, String method, String contentType,
			String content) {
		ContinuationToken token = new ContinuationToken(null, null);
		return getData(url, method, contentType, content, token);
	}

	protected String getData(String url, String method, String contentType,
			String content, ContinuationToken cTokens) {
		try {
			HttpClient client = new DefaultHttpClient();
			HttpResponse res = null;
			url = url.replaceAll(" ", "%20");
			if (method == "GET") {
				HttpGet get = new HttpGet(url);
				get.addHeader("Authorization", token);
				get.addHeader("Content-Type", contentType);
				get.addHeader("Content-Length", content.length() + "");
				res = client.execute(get);
			} else if (method == "POST") {
				HttpPost post = new HttpPost(url);
				post.addHeader("Authorization", token);
				post.addHeader("Content-Type", contentType);
				StringEntity entity = new StringEntity(content, HTTP.UTF_8);
				post.setEntity(entity);
				res = client.execute(post);
			} else if (method == "DELETE") {
				HttpDelete del = new HttpDelete(url);
				del.addHeader("Authorization", token);
				del.addHeader("Content-Type", contentType);
				res = client.execute(del);
			} else if (method == "PUT") {
				HttpPut put = new HttpPut(url);
				put.addHeader("Authorization", token);
				put.addHeader("Content-Type", contentType);
				StringEntity entity = new StringEntity(content, HTTP.UTF_8);
				put.setEntity(entity);
				res = client.execute(put);
			}
			StatusLine sl = res.getStatusLine();
			String json = "";
			if (sl.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				res.getEntity().writeTo(out);
				out.close();
				json = out.toString();
				Log.i("result", json);
				return json;
			} else {
				Log.i("error", sl.getReasonPhrase());
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}