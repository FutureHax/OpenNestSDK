package com.t3hh4xx0r.opennestsdk.sdk.api;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.t3hh4xx0r.opennestsdk.sdk.objects.LoginResponse;
import com.t3hh4xx0r.opennestsdk.sdk.objects.StatusResponse;

public class PreferencesManager {
	Context c;

	public PreferencesManager(Context c) {
		this.c = c;
	}

	public void setLoginResponse(LoginResponse loginResponse) {
		Editor e = PreferenceManager.getDefaultSharedPreferences(c).edit();
		e.putString("login_response", new Gson().toJson(loginResponse, LoginResponse.class));
		e.commit();
	}
	
	public LoginResponse getLoginResponse() {
		return new Gson().fromJson(PreferenceManager.getDefaultSharedPreferences(c).getString("login_response", ""), LoginResponse.class);
	}
	
	public void setStatusResponse(StatusResponse statusResponse) {
		Editor e = PreferenceManager.getDefaultSharedPreferences(c).edit();
		e.putString("status_response", new Gson().toJson(statusResponse, StatusResponse.class));
		e.commit();
	}
	
	public StatusResponse getStatusResponse() {
		return new Gson().fromJson(PreferenceManager.getDefaultSharedPreferences(c).getString("status_response", ""), StatusResponse.class);
	}

}
