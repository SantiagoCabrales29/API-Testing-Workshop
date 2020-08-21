package restfulbookerTests;

import helpers.TestEnv;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import restfulBookerApi.RestfulBookerApi;
import restfulBookerApi.entities.RestfulBookerBooking;
import restfulBookerApi.entities.RestfulBookerBookingId;

import java.util.List;

public class BookingCRUDTests {
	@Test
	public void getBookings() {
		RestfulBookerApi api = new RestfulBookerApi(TestEnv.getURL(), TestEnv.getUsername(), TestEnv.getPassword());

		Response response = api.getBookingsEndpointResponse();

		List<RestfulBookerBookingId> listIds = api.getBookingsIdsList(response);

		Assert.assertTrue(listIds.size() > 0);

	}

	@Test
	public void getBookingByRandomId() {
		RestfulBookerApi api = new RestfulBookerApi(TestEnv.getURL(), TestEnv.getUsername(), TestEnv.getPassword());

		RestfulBookerBooking booking = api.getRandomBooking();

		Assert.assertNotNull(booking);
	}

	@Test
	public void getBookingByGivenId() {
		RestfulBookerApi api = new RestfulBookerApi(TestEnv.getURL(), TestEnv.getUsername(), TestEnv.getPassword());
		String id = "1";

		RestfulBookerBooking booking = api.getBookingById(id);

		Assert.assertNotEquals("Eric", booking.getFirstname());
	}

	@Test
	public void createBooking(){
		RestfulBookerApi api = new RestfulBookerApi(TestEnv.getURL(), TestEnv.getUsername(), TestEnv.getPassword());

		Response response = api.createBooking();

		int newBookingId = response.then().extract().path("bookingid");
		//System.out.println("El id del nuevo booking es: "+ newBookingId);

		String newBookingFN = response.then().extract().path("booking.firstname");
		//System.out.println("El nombre del nuevo booking es: "+ newBookingFN);

		RestfulBookerBooking newBooking = api.getBookingById(String.valueOf(newBookingId));

		Assert.assertEquals(newBooking.getFirstname(), newBookingFN);

		//Assert.assertEquals(200,response.getStatusCode());
	}

	@Test
	public  void updateBooking(){
		RestfulBookerApi api = new RestfulBookerApi(TestEnv.getURL(), TestEnv.getUsername(), TestEnv.getPassword());

		String token = api.auth();
		Response response = api.getBookingsEndpointResponse();
		List<RestfulBookerBookingId> listIds = api.getBookingsIdsList(response);

		int aux = 1;

		if (searchItemToUpdate(listIds,aux)){
			RestfulBookerBooking booking1 = api.getBookingById("1");
			booking1.setFirstname("Santi");
			booking1.setLastname("Cabra");

			Response response2 = api.updateBooking(booking1,token);

			Assert.assertEquals(200, response2.getStatusCode());
		} else {
			RestfulBookerBooking booking1 = api.getBookingById("2");
			booking1.setFirstname("Santi");
			booking1.setLastname("Cabra");

			Response response2 = api.updateBooking(booking1,token);
			aux = 2;

			Assert.assertEquals(200, response2.getStatusCode());
		}
	}

	@Test
	public void deleteBooking(){
		RestfulBookerApi api = new RestfulBookerApi(TestEnv.getURL(), TestEnv.getUsername(), TestEnv.getPassword());

		String token = api.auth();
		Response response = api.getBookingsEndpointResponse();
		List<RestfulBookerBookingId> listIds = api.getBookingsIdsList(response);

		int aux = 1;

		if (searchItemToUpdate(listIds,aux)){

			Response response2 = api.deleteBooking(token, "1");

			Assert.assertEquals(201, response2.getStatusCode());

			Response response3 = api.getBookingsEndpointResponse();

			List<RestfulBookerBookingId> updatedList = api.getBookingsIdsList(response3);

			Assert.assertFalse(searchItemToUpdate(updatedList, 1));
		} else {
			Response response1 = api.deleteBooking(token,"2");

			//Assert.assertEquals(201, response1.getStatusCode());
		}
	}

	public boolean searchItemToUpdate(List<RestfulBookerBookingId> listIds, int idToSearch){

		for (RestfulBookerBookingId id:listIds) {
			if (id.getBookingid()==idToSearch){
				return true;
			}
		}
		return false;
	}
}
