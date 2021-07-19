Feature: AddressAPI

@AddPlaceAPI
Scenario: Add New Address and validate response
Given Add place payload
When call "AddPlaecAPI" with "Post" http request
And Api call got success with status code 200
Then "scope" field value in response is "APP"


#Updated : to make data driven
@AddPlaceAPI_DataDriven
Scenario Outline: Add New Address and validate response with dynamic data
Given Add place payload with "<name>" "<Language>" "<address>"
When call "AddPlaecAPI" with "Post" http request
And Api call got success with status code 200
Then "scope" field value in response is "APP"

Examples:
|name| Language | address |
|Honey | English | WorldWide |
|Himanshu | Spanish | India |


# Updated : to make end to end test [add place, get place]
@Add_Get_PlaceAPI
Scenario Outline: Add Address and validate response using Get Place API
Given Add place payload with "<name>" "<Language>" "<address>"
When call "AddPlaecAPI" with "Post" http request
And Api call got success with status code 200
Then "scope" field value in response is "APP"
Then Verify placeID got created "<verifyField>" having "<name>" using "GetPlaecAPI"

Examples:
|name| Language | address | verifyField|
|Honey2 | English | WorldWide |name|

#Use this when above addplace api TCs is not run, before hook will be run before this anotation
@DeletePlace_UsingHooks
Scenario: Delete Address and validate response
Given Delete place payload
When call "DeletePlaecAPI" with "Post" http request
And Api call got success with status code 200
Then "status" field value in response is "OK"