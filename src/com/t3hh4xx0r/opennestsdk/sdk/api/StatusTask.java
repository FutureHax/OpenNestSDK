package com.t3hh4xx0r.opennestsdk.sdk.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import android.os.AsyncTask;
import android.util.Log;

import com.t3hh4xx0r.opennestsdk.sdk.objects.LoginResponse;
import com.t3hh4xx0r.opennestsdk.sdk.objects.StatusResponse;

public class StatusTask extends AsyncTask<Void, Void, Void> {
	LoginResponse loginResp;
	iStatusTask listener;
	Nest nest;
	
	public iStatusTask getListener() {
		return listener;
	}

	public void setListener(iStatusTask listener) {
		this.listener = listener;
	}

	public StatusTask(LoginResponse loginResp, Nest nest) {
		this.loginResp = loginResp;
		this.nest = nest;
	}

	public interface iStatusTask {
		public void onStatusFetched(StatusResponse resp);
		public void onFailure(String message);
	}

	@Override
	protected Void doInBackground(Void... params) {
		URL url;
		HttpsURLConnection urlc;

		try {
			url = new URL(loginResp.getUrls().getTransport_url()
					+ "/v2/mobile/user." + loginResp.getUserid());
			urlc = (HttpsURLConnection) url.openConnection();
			urlc.setRequestMethod("GET");
			urlc.setDoInput(true);
			urlc.setDoOutput(false);
			urlc.setRequestProperty("Authorization",
					"Basic " + loginResp.getAccess_token());
			urlc.setRequestProperty("X-nl-user-id", "r2doesinc@gmail.com");
			urlc.setRequestProperty("Host", loginResp.getUrls().getTransport_url());
			urlc.setRequestProperty("X-nl-protocol-version", "1");

			StringBuffer buffer = Nest.getStringBufferFromResponse(urlc);
			StatusResponse statusResponse = Nest.gson.fromJson(
					buffer.toString(), StatusResponse.class);
			Log.d("STATUS REPSONSE HERE", statusResponse.toString());
			nest.setStatusResponse(statusResponse);
			if (listener != null) {
				listener.onStatusFetched(statusResponse);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Rsponse resp = localGson.fromJson(buffer.toString()
		// .trim(), LoginResponse.class);
		// Log.d("THE STATUS RESP", resp.toString());
		//
		// switch (urlc.getResponseCode()) {
		// case HttpsURLConnection.HTTP_OK:
		//
		// break;
		// default:
		//
		// }
		//
		// } catch (MalformedURLException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		return null;
	}
}