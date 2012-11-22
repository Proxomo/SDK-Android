package com.proxomoandroidsdk.helpers;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import com.proxomoandroidsdk.definitions.ContinuationToken;
import com.proxomoandroidsdk.enums.Enums.CommunicationType;

import android.util.Log;

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

	public String getData(String url, String method, String contentType) {
		ContinuationToken token = new ContinuationToken(null, null);
		return getData(url, method, contentType, "", token);
	}

	public String getData(String url, String method, String contentType, String content) {
		ContinuationToken token = new ContinuationToken(null, null);
		return getData(url, method, contentType, content, token);
	}

	public String getData(String url, String method, String contentType,
			String content, ContinuationToken cTokens) {
		String nextPartitionKeyDescriptor = "NextPartitionKey";
		String nextRowKeyDescriptor = "NextRowKey";
		url = "https://service.proxomo.com/v09/json/customdata/search/table/MyCustomTable?q=&maxresults=20";
		method = "GET";
		token = "SBnyYRxrQNVYUYEaSi2cx7wdcywaPgV4ZFuNKydQlfY=";
		contentType = "application/json";
		content = "";
		try {
			HttpClient client = new DefaultHttpClient();
			HttpContext ctxt = new BasicHttpContext();
			HttpResponse res = null;

			if (method == "GET") {
				HttpGet get = new HttpGet(url);
				get.addHeader("Authorization", token);
				get.addHeader("Content-Type", contentType);
				get.addHeader("Content-Length", content.length() + "");
				res = client.execute(get);

			} else {
				HttpPost post = new HttpPost(url);
				post.addHeader("Authorization", token);
				post.addHeader("Content-Type", contentType);
				post.addHeader("Content-Length", content.length() + "");
				res = client.execute(post);
			}
			StatusLine sl = res.getStatusLine();
			String json = "";
			if (sl.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				res.getEntity().writeTo(out);
				out.close();
				json = out.toString();
				Log.i("result", out.toString());
				// returnJson(json);
			} else {
				Log.i("error", "something wrong");
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		//
		return null;
	}

	private <T> T returnJson(String s) {
		Gson g = new GsonBuilder().registerTypeAdapter(Date.class,
				new NetDateTimeAdapter()).create();
		Type type = new TypeToken<T>() {
		}.getType();
		System.out.println(type);
		// return g.fromJson(s, t);
		return null;
	}
}