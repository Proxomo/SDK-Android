package com.proxomoandroidsdk.helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.proxomoandroidsdk.enums.Enums.LocationSearchScope;

public class Utility {
	public static String formatQueryString(String address, String lat,
			String lng, String q, String category, double radius,
			LocationSearchScope scope, int maxresults, String personId) {
		StringBuilder sb = new StringBuilder();
		sb.append("?");
		if (address != null && address != "")
			sb.append(String.format("&address=%1$s", address));
		if (lat != null && lat != "")
			sb.append(String.format("&latitude=%1$s", lat));
		if (lng != null && lng != "")
			sb.append(String.format("&longtitude=%1$s", lng));
		if (q != null && q != "")
			sb.append(String.format("&q=%1$s", q));
		if (category != null && category != "")
			sb.append(String.format("&category=%1$s", category));
		if (radius > 0)
			sb.append(String.format("&radius=%1$s", radius));
		if (scope != null)
			sb.append(String.format("&scope=%1$s", scope.convert()));
		if (maxresults > 0)
			sb.append(String.format("&maxresults=%1$s", maxresults));
		if (personId != null && personId != "")
			sb.append(String.format("&personid=%1$s", personId));
		if (sb.length() > 1)
			return sb.toString().replace("?&", "?");
		else {
			sb = null;
			return "";
		}
	}
	public static Long convertToUnixTimeStamp(Date d){
		DateFormat df=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
		try {
			return (d.getTime()-df.parse("1/1/1970 12:00:00 AM").getTime())/1000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
