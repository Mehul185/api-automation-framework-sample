Feature: Validation of API

@AddPlace @Regression
Scenario Outline: To successfully add a place using add place api
	Given The payload for add place api with "<name>" "<language>" "<address>"
	When The user calls "AddPlaceApi" with "POST" request
	Then Validate that the status code is 200
	And Validate that "status" is "OK"
	And Validate that "scope" is "APP"
	And verify that the place_id created maps to "<name>" using "GetPlaceApi" call

Examples:
| name | language | address |
| Test 120 | English-US | Mumbai | 

@DeletePlace @Regression
Scenario: To validate delete place api
	Given Delete Place Api Payload
	When The user calls "DeletePlaceApi" with "POST" request
	Then Validate that the status code is 200
	And Validate that "status" is "OK"