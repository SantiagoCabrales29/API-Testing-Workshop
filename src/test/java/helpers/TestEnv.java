package helpers;

import restfulBookerApi.html.URLifier;

import java.net.URL;

public class TestEnv {
	public static final String theUrl = "https://restful-booker.herokuapp.com";
	public static final String username = "admin";
	public static final String password = "passwords123";
	public static final String token = "a5b9ee944e61544";


	public static String getUsername() {
		return username;
	}

	public static String getPassword() {
		return password;
	}

	public static String getToken() {
		return token;
	}

	public static URL getURL() {
		return URLifier.getURLfromString(theUrl);
	}
}
