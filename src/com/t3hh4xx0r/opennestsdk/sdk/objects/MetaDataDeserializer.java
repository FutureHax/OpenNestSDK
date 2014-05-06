package com.t3hh4xx0r.opennestsdk.sdk.objects;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.t3hh4xx0r.opennestsdk.sdk.api.Nest;

public class MetaDataDeserializer implements JsonDeserializer<MetaData> {

    public MetaData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
    	MetaData metaData = new MetaData();
    	for( Map.Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet() ) {
    		MetaDataData device = Nest.gson.fromJson(entry.getValue(), MetaDataData.class);
    		metaData.setDevice(entry.getKey(), device);
		}
    	return metaData;
    }

} 
