import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FirstTest {

	@Test
	public void firstTest(){
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
}
