import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

public class FirstTest {

	@Test
	public void firstTest(){
		given().
				contentType(ContentType.JSON).
		when()
				.get("https://official-joke-api.appspot.com/random_joke").
		then().

				assertThat().
				statusCode(200);
	}


	@Test
	public void secondTest(){
		given().
				contentType(ContentType.JSON).
		when()
				.get("https://official-joke-api.appspot.com/random_joke").
		then().
				log().
				all().
				assertThat().
				statusCode(200);
	}

	@Test
	public void thirdTest(){
		given().
				contentType(ContentType.JSON).log().uri().
		when()
				.get("https://official-joke-api.appspot.com/random_joke").
		then().
				log().
				body().
				assertThat().
				body("id", greaterThan(0)).
				statusCode(200);
	}

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
}
