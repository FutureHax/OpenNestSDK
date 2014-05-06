package com.t3hh4xx0r.opennestsdk.sdk.objects;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.t3hh4xx0r.opennestsdk.sdk.api.Nest;

public class StructureDeserializer implements JsonDeserializer<Structure> {

	public Structure deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
		Structure structures = new Structure();
    	for( Map.Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet() ) {
    		StructureData structure = Nest.gson.fromJson(entry.getValue(), StructureData.class);
    		structures.setStructure(entry.getKey(), structure);
		}
    	return structures;
    }
	
}
