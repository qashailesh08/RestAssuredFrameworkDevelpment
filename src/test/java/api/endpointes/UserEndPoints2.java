package api.endpointes;

import static io.restassured.RestAssured.*;
import java.util.ResourceBundle;
import api.payloads.UserModule;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// here we will  perform CRUD 	, create , read , update, delete.
public class UserEndPoints2 {

	// This method will load the all url's from properties file
	public static ResourceBundle getURL() {
		ResourceBundle route = ResourceBundle.getBundle("routes");// load the url's
		return route;
	}

// creating new user
	public static Response create_user(UserModule payload) {
		String post_user = getURL().getString("post_user");
		Response res = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(post_user);
		return res;

	}

	// getting user detail

	public static Response readUser(String username) {
		String get_user = getURL().getString("get_user");
		Response res = given().pathParam("username", username).when().get(get_user);
		return res;
	}

	// update user

	public static Response updateUser(String username, UserModule payload) {
		String update_user = getURL().getString("update_user");
		Response res = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
				.pathParam("username", username).when().put(update_user);
		return res;
	}

	// delete user
	public static Response deleteUser(String username) {
		String delete_user = getURL().getString("delete_user");
		Response res = given().pathParam("username", username).when().get(delete_user);
		return res;
	}
}
