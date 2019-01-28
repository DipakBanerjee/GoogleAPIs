package googleAPIs;

import io.restassured.http.ContentType;
import payloads.addPlacePayload;
import resources.utility_class;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

public class addPlaceTest extends utility_class {

	@Test
	public void postData()	{

		/*
		3 types of parameters used in REST API requests: -
			Path - no ? between resource and parameter
			Query - there IS a ? between resource and parameter
			Header - 

		For a POST request we need to specify what type of parameters we enter (unlike GET).
		Parameter not being sent as part of endpoint (key is different as it doesn't actually send any data)
		=> we don't use path parameter in a POST request
		 */

		given().
		queryParam("key","AIzaSyBeVk6FnC5xmYxkXwE753cJaxYTXRt7ZKQ").
		body(addPlacePayload.getPostData()). //we can do this because the method is static so we can call this method in any class
		when().
		post("add/json").
		then().
		assertThat().statusCode(200)
		.and().contentType(ContentType.JSON)
		.and().body("status",equalTo("OK"));
	}
}