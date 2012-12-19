package com.proxomoandroidsdk.helpers;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class NetDateTimeAdapter extends TypeAdapter<Date> {
	@Override
	public Date read(JsonReader reader) throws IOException {
		if (reader.peek() == JsonToken.NULL) {
			reader.nextNull();
			return null;
		}
		Date result = null;
		String str = reader.nextString();
		String timePart = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
		String[] split = null;
		char sign;
		if (timePart.indexOf("+") != -1) {
			split = timePart.split("[^0-9]");
			sign = '+';
		} else if (timePart.indexOf("-") != -1) {
			split = timePart.split("[^0-9]");
			sign = '-';
		} else {
			split = new String[2];
			split[0] = timePart;
			split[1] = "0";
			sign = '+';
		}
		str = split[0];
		if (str != "") {
			try {
				result = new Date(Long.parseLong(str));
				Calendar c = Calendar.getInstance();
				c.setTime(result);
				c.add(Calendar.HOUR, Integer.parseInt(sign + split[1]));
			} catch (NumberFormatException e) {
			}
		}
		return result;
	}

	@Override
	public void write(JsonWriter writer, Date value) throws IOException {
		// not implemented
		Long l = value.getTime();
	    writer.value("/Date("+l+"+0000)/");

	}

}
