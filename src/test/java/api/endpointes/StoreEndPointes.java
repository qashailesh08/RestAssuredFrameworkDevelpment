package api.endpointes;

import static io.restassured.RestAssured.given;
import java.util.ResourceBundle;
import api.payloads.StoreModule;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndPointes {

	public static ResourceBundle getURlL() {
		ResourceBundle url = ResourceBundle.getBundle("routes");
		return url;
	}

	// create a new order
	public static Response create_order(StoreModule payload) {
		String post_store = getURlL().getString("post_store");
		Response res = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(post_store);
		return res;
	}

	// get order
	public static Response get_order(int id) {
		String get_store = getURlL().getString("get_store");
		Response res = given().pathParam("id", id).when().get(get_store);
		return res;
	}

	// Delete order
	public static Response delete_order(int id) {
		String delete_store = getURlL().getString("delete_store");
		Response res = given().pathParam("id", id).when().delete(delete_store);
		return res;
	}

}
