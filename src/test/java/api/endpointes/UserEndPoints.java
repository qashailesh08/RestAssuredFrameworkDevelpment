package api.endpointes;

import static io.restassured.RestAssured.given;

import api.payloads.UserModule;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// here we will  perform CRUD 	, create , read , update, delete.
public class UserEndPoints {
// creating new user
	public static Response create_user(UserModule payload) {

		Response res = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(Routes.userPost_url);
		return res;

	}

	// getting user detail

	public static Response readUser(String username) {
		Response res = given().pathParam("username", username).when().get(Routes.userGet_url);
		return res;
	}

	// update user

	public static Response updateUser(String username, UserModule payload) {

		Response res = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
				.pathParam("username", username).when().put(Routes.userUpdate_url);
		return res;
	}

	// delete user
	public static Response deleteUser(String username) {
		Response res = given().pathParam("username", username).when().get(Routes.userDelete_url);
		return res;
	}
}
