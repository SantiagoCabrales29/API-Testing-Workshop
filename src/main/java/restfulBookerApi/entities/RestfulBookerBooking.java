package restfulBookerApi.entities;

public class RestfulBookerBooking {
	private String firstname;
	private String lastname;
	private int totalprice;
	private boolean depositpaid;
	private Object bookingdates;
	private String additionalneeds;

	public RestfulBookerBooking(String firstname, String lastname, int totalprice, boolean depositpaid, Object bookingdates, String additionalneeds) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.totalprice = totalprice;
		this.depositpaid = depositpaid;
		this.bookingdates = bookingdates;
		this.additionalneeds = additionalneeds;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}

	public void setAdditionalneeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public int getTotalprice() {
		return totalprice;
	}

	public boolean isDepositpaid() {
		return depositpaid;
	}

	public String getAdditionalneeds() {
		return additionalneeds;
	}
}
