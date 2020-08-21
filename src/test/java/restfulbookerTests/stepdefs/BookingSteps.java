package restfulbookerTests.stepdefs;

import helpers.TestEnv;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import restfulBookerApi.RestfulBookerApi;
import restfulBookerApi.entities.RestfulBookerBooking;
import restfulBookerApi.entities.RestfulBookerBookingId;

import java.util.List;

public class BookingSteps {
	private int id;
	private List<RestfulBookerBookingId> listId;
	@Given("A recently created Booking")
	public void aRecentlyCreatedBooking() {
		id = createBooking();
	}

	@When("The user access sends a request to the booking list endpoint")
	public void theUserAccessSendsARequestToTheBookingListEndpoint() {
		RestfulBookerApi api = new RestfulBookerApi(TestEnv.getURL(), TestEnv.getUsername(), TestEnv.getPassword());
		listId = getBookings();

	}

	@Then("The booking is added to the list")
	public void theBookingIsAddedToTheList() {
		//searchItemToUpdate(listId,id);
		Assert.assertTrue(searchItemToUpdate(listId,id));
	}

	public List<RestfulBookerBookingId> getBookings() {
		RestfulBookerApi api = new RestfulBookerApi(TestEnv.getURL(), TestEnv.getUsername(), TestEnv.getPassword());

		Response response = api.getBookingsEndpointResponse();

		List<RestfulBookerBookingId> listIds = api.getBookingsIdsList(response);

		Assert.assertTrue(listIds.size() > 0);

		return listIds;

	}

	public int createBooking(){
		RestfulBookerApi api = new RestfulBookerApi(TestEnv.getURL(), TestEnv.getUsername(), TestEnv.getPassword());

		Response response = api.createBooking();

		int newBookingId = response.then().extract().path("bookingid");
		//System.out.println("El id del nuevo booking es: "+ newBookingId);

		String newBookingFN = response.then().extract().path("booking.firstname");
		//System.out.println("El nombre del nuevo booking es: "+ newBookingFN);

		RestfulBookerBooking newBooking = api.getBookingById(String.valueOf(newBookingId));

		Assert.assertEquals(newBooking.getFirstname(), newBookingFN);

		//Assert.assertEquals(200,response.getStatusCode());
		return newBookingId;
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
