package com.t3hh4xx0r.opennestsdk.sdk.objects;

public class LoginResponse {

	public class Urls {
		String transport_url, rubyapi_url, weather_url, log_upload_url,
				support_url;

		public String getTransport_url() {
			return transport_url;
		}

		public void setTransport_url(String transport_url) {
			this.transport_url = transport_url;
		}

		public String getRubyapi_url() {
			return rubyapi_url;
		}

		public void setRubyapi_url(String rubyapi_url) {
			this.rubyapi_url = rubyapi_url;
		}

		public String getWeather_url() {
			return weather_url;
		}

		public void setWeather_url(String weather_url) {
			this.weather_url = weather_url;
		}

		public String getLog_upload_url() {
			return log_upload_url;
		}

		public void setLog_upload_url(String log_upload_url) {
			this.log_upload_url = log_upload_url;
		}

		public String getSupport_url() {
			return support_url;
		}

		public void setSupport_url(String support_url) {
			this.support_url = support_url;
		}

		@Override
		public String toString() {
			return "Urls [transport_url=" + transport_url + ", rubyapi_url="
					+ rubyapi_url + ", weather_url=" + weather_url
					+ ", log_upload_url=" + log_upload_url + ", support_url="
					+ support_url + "]";
		}

		public Urls() {
		}
	}

	public class Limits {
		String thermostats_per_structure, structures, thermostats;

		public Limits() {

		}

		public String getThermostats_per_structure() {
			return thermostats_per_structure;
		}

		public void setThermostats_per_structure(
				String thermostats_per_structure) {
			this.thermostats_per_structure = thermostats_per_structure;
		}

		public String getStructures() {
			return structures;
		}

		public void setStructures(String structures) {
			this.structures = structures;
		}

		public String getThermostats() {
			return thermostats;
		}

		public void setThermostats(String thermostats) {
			this.thermostats = thermostats;
		}

		@Override
		public String toString() {
			return "Limits [thermostats_per_structure="
					+ thermostats_per_structure + ", structures=" + structures
					+ ", thermostats=" + thermostats + "]";
		}
	}

	String is_superuser, is_staff;
	String access_token, userid, email, user;
	String expires_in;
	Urls urls;

	public String getIs_superuser() {
		return is_superuser;
	}

	public void setIs_superuser(String is_superuser) {
		this.is_superuser = is_superuser;
	}

	public String getIs_staff() {
		return is_staff;
	}

	public void setIs_staff(String is_staff) {
		this.is_staff = is_staff;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public Urls getUrls() {
		return urls;
	}

	public void setUrls(Urls urls) {
		this.urls = urls;
	}

	public Limits getLimits() {
		return limits;
	}

	public void setLimits(Limits limits) {
		this.limits = limits;
	}

	Limits limits;

	@Override
	public String toString() {
		return "LoginResponse [is_superuser=" + is_superuser + ", is_staff="
				+ is_staff + ", access_token=" + access_token + ", userid="
				+ userid + ", email=" + email + ", user=" + user
				+ ", expires_in=" + expires_in + ", urls=" + urls + ", limits="
				+ limits + "]";
	}

	public LoginResponse() {

	}
}
