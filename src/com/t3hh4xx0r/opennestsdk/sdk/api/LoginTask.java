package com.t3hh4xx0r.opennestsdk.sdk.api;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.t3hh4xx0r.opennestsdk.sdk.objects.LoginResponse;
import com.t3hh4xx0r.opennestsdk.sdk.objects.User;

public class LoginTask extends AsyncTask<Void, Void, LoginResponse> {
	public interface iLoginTask {
		public void onLoginFailure(String message);

		public void onLoginSuccess(LoginResponse response, User u);
	}
	
	private String uri = "https://home.nest.com/user/login";

	String error;
	
	Nest nest;
	
	User user;

	iLoginTask listener;
	
	public LoginTask(User user, Nest nest) {
		this.user = user;
		this.nest = nest;
	}

	@Override
	protected LoginResponse doInBackground(Void... params) {
		try {

			Properties properties = user.toProperties();
			String query = Nest.makeQueryString(properties);

			URL url = new URL(uri);
			HttpsURLConnection urlc = (HttpsURLConnection) url.openConnection();
			urlc.setRequestMethod("POST");
			urlc.setDoInput(true);
			urlc.setDoOutput(true);
			DataOutputStream output = new DataOutputStream(
					urlc.getOutputStream());
			output.writeBytes(query);

			if (urlc.getResponseCode() == HttpsURLConnection.HTTP_OK) {
				StringBuffer buffer = Nest.getStringBufferFromResponse(urlc);
				return new Gson().fromJson(
						buffer.toString(), LoginResponse.class);
			} else {
				if (urlc.getResponseMessage() != null) {
					error = urlc.getResponseMessage();
				}
				return null;
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public iLoginTask getListener() {
		return listener;
	}

	@Override
	protected void onPostExecute(LoginResponse result) {
		super.onPostExecute(result);
		nest.setLoginResponse(result);

		if (result != null) {
			listener.onLoginSuccess(result, user);
		} else {
			if (error != null) {
				listener.onLoginFailure(error);
			}
		}
	}

	public void setListener(iLoginTask listener) {
		this.listener = listener;
	}
}
