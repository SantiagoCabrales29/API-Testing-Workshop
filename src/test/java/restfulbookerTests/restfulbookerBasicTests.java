package restfulbookerTests;

import helpers.TestEnv;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class restfulbookerBasicTests {
	private static RequestSpecification requestSpecification;
	private static ResponseSpecification responseSpecification;

	@BeforeClass
	public static void createRequestSpecification(){
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
		given().
				contentType(ContentType.JSON).
				spec(requestSpecification).
		when().
				get("").
		then().
				spec(responseSpecification);
	}

	@Test
	public void testBookerIdValue(){
		given().
				spec(requestSpecification).
		when()
				.get("").
		then().
				spec(responseSpecification).
				assertThat().body("bookingid[0]",equalTo(12));
	}

	@Test
	public void BookingByIdEndpointFirstTest(){
		given().
				contentType(ContentType.JSON).
				when()
				.get(TestEnv.getURL()+"/booking/1").
				then().
				assertThat().
				statusCode(200);
	}

	@Test
	public void checkFirstNameTest(){
		given().
				contentType(ContentType.JSON).
				when()
				.get(TestEnv.getURL()+"/booking/1").
				then().
				assertThat().body("firstname", not(equalTo("Santi"))).
							body("totalprice",greaterThan(0));
	}


}
