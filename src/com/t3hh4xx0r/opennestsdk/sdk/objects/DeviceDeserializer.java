package com.t3hh4xx0r.opennestsdk.sdk.objects;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.t3hh4xx0r.opennestsdk.sdk.api.Nest;

public class DeviceDeserializer implements JsonDeserializer<Device> {

    public Device deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
    	Device devices = new Device();
    	for( Map.Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet() ) {
    		DeviceData device = Nest.gson.fromJson(entry.getValue(), DeviceData.class);
    		devices.setDevice(entry.getKey(), device);
		}
    	return devices;
    }

} 
