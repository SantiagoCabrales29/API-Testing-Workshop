package restfulBookerApi.helpers;

public class RestfulBookerApiEndpoints {

	public static final String bookings = "/booking";
	public static final String bookingId = "/booking/%s";
	public static final String auth = "/auth";


	public static String bookings(String id){
		return String.format(bookingId, id);
	}


}
