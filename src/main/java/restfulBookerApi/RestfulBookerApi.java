package restfulBookerApi;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import restfulBookerApi.entities.RestfulBookerBooking;
import restfulBookerApi.entities.RestfulBookerBookingId;
import restfulBookerApi.html.HttpMessageSender;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RestfulBookerApi {
	private final URL url;
	private final HttpMessageSender httpMessageSender;
	private Gson gson = new Gson();
	private JsonParser parser = new JsonParser();

	public RestfulBookerApi(URL restfulBookerUrl, String username, String password) {
		this.url = restfulBookerUrl;
		httpMessageSender = new HttpMessageSender(restfulBookerUrl);
	}


	public Response getBookingsEndpointResponse() {

		Response bookingsListResponse = httpMessageSender.getMessageTo("/booking");

		return bookingsListResponse;
	}

	public Response getBookingByIdEndpointResponse(int num) {

		String aux = String.valueOf(num);

		Response bookingsListResponse = httpMessageSender.getMessageTo("/booking/"+aux);

		return bookingsListResponse;
	}

}
