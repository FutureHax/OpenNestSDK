package com.t3hh4xx0r.opennestsdk.sdk.api;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.t3hh4xx0r.opennestsdk.sdk.objects.TempChange;

public class SetTempTask extends AsyncTask<Void, Void, Void> {
	Nest nest;
	iSetTempTask listener;
	int targetTempHigh, targetTempLow;

	public interface iSetTempTask {
		public void onTempSet(int temp);

		public void onFailure(String message);
	}

	public SetTempTask(int targetTempHigh, int targetTempLow, Nest nest) {
		this.nest = nest;
		this.targetTempHigh = targetTempHigh;
		this.targetTempLow = targetTempLow;
	}

	public static double toC(int f) {
		return (((f - 32.0) * 5.0) / 9.0);
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			String query = "{\"target_change_pending\":" + Boolean.TRUE
					+ ",\"target_temperature_high\":" + toC(targetTempHigh)
					+ ",\"target_temperature_low\":" + toC(targetTempLow) + "}";

			URL url = new URL(nest.getLoginResponse().getUrls()
					.getTransport_url()
					+ "/v2/put/shared."
					+ nest.getStatusResponse().getMetaData().getDeviceIds()[0]);
			Log.d("THE QUERY", query);
			Log.d("THE URL", url.toExternalForm());

			HttpsURLConnection urlc = (HttpsURLConnection) url.openConnection();
			urlc.setRequestMethod("POST");
			urlc.setDoInput(true);
			urlc.setDoOutput(true);

			urlc.setRequestProperty("Authorization", "Basic "
					+ nest.getLoginResponse().getAccess_token());
			urlc.setRequestProperty("X-nl-protocol-version", "1");
			urlc.setRequestProperty("Content-length",
					String.valueOf(query.length()));
			urlc.setRequestProperty("Content-Type",
					"application/json");

			DataOutputStream output = new DataOutputStream(
					urlc.getOutputStream());
			output.writeBytes(query);

			Log.d("THE RESPONSE CODE", Integer.toString(urlc.getResponseCode()));
			// if (urlc.getResponseCode() == HttpsURLConnection.HTTP_OK) {
			StringBuffer buffer = Nest.getStringBufferFromResponse(urlc);
			Log.d("THE RESULT FROM TEMP SETTING", buffer.toString());
			// // // return new Gson().fromJson(
			// // // buffer.toString(), LoginResponse.class);
			// // } else {
			// // // if (urlc.getResponseMessage() != null) {
			// // // error = urlc.getResponseMessage();
			// // // }
			// // return null;
			// // }
			//
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
