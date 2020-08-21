
Feature: Booking test with ghekin

  Scenario: Create Booking
	Given A recently created Booking
	When The user access sends a request to the booking list endpoint
	Then The booking is added to the list


