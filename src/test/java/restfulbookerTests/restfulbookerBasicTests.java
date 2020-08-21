package restfulbookerTests;

import helpers.TestEnv;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import restfulBookerApi.RestfulBookerApi;
import restfulBookerApi.html.HttpMessageSender;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class restfulbookerBasicTests {
	private static RequestSpecification requestSpecification;
	private static ResponseSpecification responseSpecification;

	private  HttpMessageSender httpMessageSender;

	private static RestfulBookerApi api ;

	@BeforeClass
	public static void createRequestSpecification(){
		api = new RestfulBookerApi(TestEnv.getURL(),TestEnv.getUsername(), TestEnv.getPassword());
		requestSpecification = new RequestSpecBuilder().
				setContentType(ContentType.JSON).
				setBaseUri("https://restful-booker.herokuapp.com/booking").
				build();
	}

	@BeforeClass
	public static void createResponseSpecification(){
		responseSpecification = new ResponseSpecBuilder().
				expectContentType(ContentType.JSON).
				expectStatusCode(200).
				build();
	}

	@Test
	public void restfulBookerFirstTest(){
		Response response = api.getBookingsEndpointResponse();

//		httpMessageSender = new HttpMessageSender(TestEnv.getURL());
//		Response response = httpMessageSender.getMessageTo("/booking");

		Assert.assertEquals(200, response.getStatusCode());

	}

	@Test
	public void testBookerIdValue(){
		Response response = api.getBookingsEndpointResponse();

		int bookingId = response.then().extract().path("bookingid[0]");
		Assert.assertEquals(bookingId, 10);
		System.out.println(bookingId);
	}

	@Test
	public void BookingByIdEndpointFirstTest(){
		Response response = api.getBookingByIdEndpointResponse(1);

//		httpMessageSender = new HttpMessageSender(TestEnv.getURL());
//		Response response = httpMessageSender.getMessageTo("/booking/1");
		Assert.assertEquals(200, response.getStatusCode());
	}

	@Test
	public void checkFirstNameTest(){
		Response response = api.getBookingByIdEndpointResponse(1);

		response.then().
				assertThat().body("firstname", not(equalTo("Santi"))).
							body("totalprice",greaterThan(0));
	}


}
