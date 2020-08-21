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

	public List<RestfulBookerBookingId> getBookingsIdsList(Response response) {

		JsonElement jsonResponse = parser.parse(response.body().asString());

		List<RestfulBookerBookingId> listBookingsIds = Arrays.asList(gson.fromJson(jsonResponse, RestfulBookerBookingId[].class));

		System.out.println("La lista tiene el tamaño: " + listBookingsIds.size());

		return listBookingsIds;

	}
	//HACER EL EJERCICIO DEL MAPEO DE RESTFULBOOKERBOOKING







	public RestfulBookerBooking getRandomBooking() {
		Response response = getBookingsEndpointResponse();
		List<RestfulBookerBookingId> listBookingIds = getBookingsIdsList(response);
		int randomNum = generateRandomNumber(listBookingIds.size());
		System.out.println("El numero random generado es: " + randomNum);
		response = httpMessageSender.getMessageTo("/booking/"+randomNum);

		JsonElement element = parser.parse(response.body().asString());

		RestfulBookerBooking booking = gson.fromJson(element, RestfulBookerBooking.class);
		System.out.println("El booking que se selecciono tiene como firstname a: " + booking.getFirstname());

		return booking;
	}

	public int generateRandomNumber(int limit) {
		return (int) (Math.random() * limit + 1);

	}

}
