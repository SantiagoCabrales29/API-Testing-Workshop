package restfulBookerApi.html;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import restfulBookerApi.entities.RestfulBookerBooking;
import restfulBookerApi.entities.RestfulBookerBookingId;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class HttpMessageSender {
	private final URL url;
	private String user;
	private String password;
	private Response lastResponse;

	public HttpMessageSender(URL aUrl){
		this.url = aUrl;
	}


	public Response getMessageTo(String endpoint){
		URL theEndpointUrl = createEndpoint(url, endpoint);

		Response ret =
				given().
						contentType(ContentType.JSON).
						when().
						get(theEndpointUrl.toExternalForm()).
						andReturn();
		return ret;
	}

	public URL createEndpoint(URL url, String endpoint){
		try {
			return new URL(url, endpoint);

		} catch (MalformedURLException e){

			e.printStackTrace();
			throw new RuntimeException(String.format("Could not construct %s endpoint using %s", endpoint, url.toExternalForm()));
		}
	}

	public Response postMessageTo(RestfulBookerBooking booking, String endpoint){
		URL theEndpointUrl = createEndpoint(url, endpoint);

		Response ret =
				given().
						contentType(ContentType.JSON).
						body(booking).
						log().body().
						when().
						post(theEndpointUrl.toExternalForm()).
						andReturn();

		return ret;
	}

	public Response auth(String credentials, String endpoint){
		URL theEndpointUrl = createEndpoint(url, endpoint);

		Response ret =
				given().
						contentType(ContentType.JSON).
						body(credentials).
						log().all().
						when().
						post(theEndpointUrl.toExternalForm()).
						andReturn();

		return ret;
	}

	public Response putMessageTo(RestfulBookerBooking booking, String token, String endpoint){
		URL theEndpointUrl = createEndpoint(url, endpoint);

		Response ret =
				given().
						body(booking).
						contentType(ContentType.JSON).
						cookie("token",token).log().all().
						when().
						put(theEndpointUrl.toExternalForm()).
						andReturn();
		return ret;
	}

	public Response deleteMessageTo(String token, String endpoint){
		URL theEndpointUrl = createEndpoint(url, endpoint);

		Response ret =
				given().
						contentType(ContentType.JSON).
						cookie("token",token).log().all().
						when().
						delete(theEndpointUrl.toExternalForm()).
						andReturn();
		return ret;
	}
}
