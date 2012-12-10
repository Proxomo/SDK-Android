package com.proxomoandroidsdk.helpers;

import java.lang.reflect.Type;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class EnumAdapter implements JsonDeserializer<EnumAbstract>, JsonSerializer<EnumAbstract> {


	@Override
	  public EnumAbstract deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
	      throws JsonParseException
	  {
	    return EnumFactory.getInstance().getEnumValue(typeOfT.toString().split("\\$")[1], json.getAsInt());
	  }

	@Override
	public JsonElement serialize(EnumAbstract e, Type t,
			JsonSerializationContext jsc) {
		return new JsonPrimitive(e.convert());
	}
}
