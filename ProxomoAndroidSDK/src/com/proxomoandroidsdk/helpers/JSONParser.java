package com.proxomoandroidsdk.helpers;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.proxomoandroidsdk.enums.Enums.EventPrivacy;
import com.proxomoandroidsdk.enums.Enums.EventStatus;

public class JSONParser {
	private static Gson g;

	/**
	 * Note when using gson parser: When pass in a class type, make sure that
	 * the properties in the class have the exact case as the JSON For example,
	 * JSON: {"Expire":"....."} then the class need to have Expire not expire
	 * Otherwise the parse will not work
	 * 
	 * @return
	 */
	public static Gson getInstance() {
		if (g == null) {
			GsonBuilder gb = new GsonBuilder();
			EnumAdapter ea=new EnumAdapter();
			gb.registerTypeAdapter(Date.class, new NetDateTimeAdapter());
			gb.registerTypeAdapter(EventStatus.class, ea);
			gb.registerTypeAdapter(EventPrivacy.class, ea);
			
			g = gb.create();
		}
		return g;
	}

	/**
	 * Fix the issue that token's json expire attribute is not in a MS format
	 * The original format is 12345567890 whereas it should be
	 * /Date(234234234243)/
	 * 
	 * @param json
	 * @return
	 */
	public static String fixExpireDate(String json) {
		String s = "";
		String[] split = json.split("\"");
		String timepart = split[7];
		timepart = "/Date(" + timepart + "000+0000)/";
		s = json.replace(split[7], timepart);
		return s;
	}

	/**
	 * this method should only be called once in your app
	 * to avoid performance issue
	 */
	public static void registerEnumToFactory(){
		EnumFactory.getInstance().registerEnum(EventStatus.class);
		EnumFactory.getInstance().registerEnum(EventPrivacy.class);
	}
}
