package com.t3hh4xx0r.opennestsdk.sdk.api;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

import android.os.AsyncTask;
import android.util.Log;

public class AwayTask extends AsyncTask<Void, Void, Void> {
	Nest nest;
	iAwayTask listener;
	boolean setAway;

	public interface iAwayTask {
		public void onAwaySet(boolean isAwayNow);

		public void onFailure(String message);
	}

	public AwayTask(boolean away, Nest nest) {
		this.nest = nest;
		setAway = away;
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			
			String query = "{\"away\":" + setAway +",\"away_timestamp\":" + System.currentTimeMillis() + ",\"away_setter\":0}";

			URL url = new URL(nest.getLoginResponse().getUrls()
					.getTransport_url()
					+ "/v2/put/structure."
					+ nest.getStatusResponse().getStructures()
							.getStructureIds()[0]);

			HttpsURLConnection urlc = (HttpsURLConnection) url.openConnection();
			urlc.setRequestMethod("POST");
			urlc.setDoInput(true);
			urlc.setDoOutput(true);

			urlc.setRequestProperty("Authorization", "Basic "
					+ nest.getLoginResponse().getAccess_token());
			urlc.setRequestProperty("X-nl-protocol-version", "1");
			urlc.setRequestProperty("Content-Length",
					String.valueOf(query.length()));
			urlc.setRequestProperty("Content-Type",
					"application/json");
			DataOutputStream output = new DataOutputStream(
					urlc.getOutputStream());
			output.writeBytes(query);

			Log.d("THE RESPONSE CODE", Integer.toString(urlc.getResponseCode()));
			// if (urlc.getResponseCode() == HttpsURLConnection.HTTP_OK) {
			StringBuffer buffer = Nest.getStringBufferFromResponse(urlc);
			Log.d("THE RESULT FROM AWAY SETTING", buffer.toString());
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
		// public function setAway($away, $serial_number=null) {
		// $serial_number = $this->getDefaultSerial($serial_number);
		// $data = json_encode(array('away' => $away, 'away_timestamp' =>
		// time(), 'away_setter' => 0));
		// $structure_id = $this->getDeviceInfo($serial_number)->location;
		// return $this->doPOST("/v2/put/structure." . $structure_id, $data);
		// }
		//

	}

}
