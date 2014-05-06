package com.t3hh4xx0r.opennestsdk.sdk.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.t3hh4xx0r.opennestsdk.sdk.api.LoginTask.iLoginTask;
import com.t3hh4xx0r.opennestsdk.sdk.api.StatusTask.iStatusTask;
import com.t3hh4xx0r.opennestsdk.sdk.objects.Device;
import com.t3hh4xx0r.opennestsdk.sdk.objects.DeviceDeserializer;
import com.t3hh4xx0r.opennestsdk.sdk.objects.LoginResponse;
import com.t3hh4xx0r.opennestsdk.sdk.objects.MetaData;
import com.t3hh4xx0r.opennestsdk.sdk.objects.MetaDataDeserializer;
import com.t3hh4xx0r.opennestsdk.sdk.objects.StatusResponse;
import com.t3hh4xx0r.opennestsdk.sdk.objects.Structure;
import com.t3hh4xx0r.opennestsdk.sdk.objects.StructureDeserializer;
import com.t3hh4xx0r.opennestsdk.sdk.objects.User;

public class Nest {
	public static Gson gson;
	LoginResponse loginResponse;
	StatusResponse statusResponse;
	Context c;

	public StatusResponse getStatusResponse() {
		if (statusResponse == null) {
			StatusResponse cached = new PreferencesManager(c).getStatusResponse();
			if (cached != null) {
				statusResponse = cached;
			}
		}
		return statusResponse;
	}

	public void setStatusResponse(StatusResponse statusResponse) {
		this.statusResponse = statusResponse;
		new PreferencesManager(c).setStatusResponse(statusResponse);
	}

	public LoginResponse getLoginResponse() {
		if (loginResponse == null) {
			LoginResponse cached = new PreferencesManager(c).getLoginResponse();
			if (cached != null) {
				loginResponse = cached;
			}
		}
		return loginResponse;
	}

	public void setLoginResponse(LoginResponse loginResponse) {
		this.loginResponse = loginResponse;
		new PreferencesManager(c).setLoginResponse(loginResponse);
	}

	public Nest(Context c) {
		GsonBuilder gsonb = new GsonBuilder();
		// gsonb.registerTypeHierarchyAdapter(Enum.class, new EnumSerializer());
		gsonb.registerTypeAdapter(Device.class, new DeviceDeserializer());
		gsonb.registerTypeAdapter(MetaData.class, new MetaDataDeserializer());
		gsonb.registerTypeAdapter(Structure.class, new StructureDeserializer());
		gson = gsonb.create();
		this.c = c;
	}

	public void login(final User user, iLoginTask listener) {
		LoginTask task = new LoginTask(user, this);
		task.setListener(listener);
		task.execute();
	}

	public void getStatus(iStatusTask listener) {
		if (getLoginResponse() == null) {
			listener.onFailure("You are not logged in");
		} else {
			StatusTask task = new StatusTask(getLoginResponse(), this);
			task.setListener(listener);
			task.execute();
		}
	}

	public void setAwayStatus(boolean away) {
		AwayTask task = new AwayTask(away, this);
		task.execute();
	}

	public static StringBuffer getStringBufferFromResponse(
			HttpsURLConnection urlc) throws IOException {

		InputStream is = urlc.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		String str;

		while ((str = in.readLine()) != null) {
			buffer.append(str);
		}

		in.close();
		return buffer;
	}

	public static StringBuffer getStringBufferFromResponse(
			HttpURLConnection urlc) throws IOException {

		InputStream is = urlc.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		String str;

		while ((str = in.readLine()) != null) {
			buffer.append(str);
		}

		in.close();
		return buffer;
	}

	public static String makeQueryString(Properties properties) {

		String string = "";
		Enumeration<?> e = properties.propertyNames();

		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			string += URLEncoder.encode(key) + "="
					+ URLEncoder.encode(properties.getProperty(key)) + "&";
		}

		return string;

	}

}
