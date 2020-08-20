package restfulbookerTests;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class restfulbookerBasicTests {

	@Test
	public void restfulBookerFirstTest(){
		given().
				contentType(ContentType.JSON).
				when()
				.get("https://restful-booker.herokuapp.com/booking").
				then().
				assertThat().
				log().all().
				statusCode(200);
	}

	@Test
	public void testBookerIdValue(){
		given().
				contentType(ContentType.JSON).
				when()
				.get("https://restful-booker.herokuapp.com/booking").
				then().
				assertThat().body("bookingid[0]",equalTo(12));
	}





	@Test
	public void BookingByIdEndpointFirstTest(){
		given().
				contentType(ContentType.JSON).
				when()
				.get("https://restful-booker.herokuapp.com/booking/1").
				then().
				assertThat().
				statusCode(200);
	}

	@Test
	public void checkFirstNameTest(){
		given().
				contentType(ContentType.JSON).
				when()
				.get("https://restful-booker.herokuapp.com/booking/1").
				then().
				assertThat().body("firstname", not(equalTo("Santi"))).
							body("totalprice",greaterThan(0));
	}


}
