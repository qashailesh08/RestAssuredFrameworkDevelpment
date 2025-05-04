package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpointes.StoreEndPointes;
import api.payloads.StoreModule;
import io.restassured.response.Response;

public class StoreTests {

	public StoreModule storepayload;

	@BeforeClass
	public void createData() {
		storepayload = new StoreModule();
		storepayload.setId(1);
		storepayload.setPetId(199);
		storepayload.setCompelete(true);
		storepayload.setQuantity(1);
		storepayload.setShipedate("4-05-2025");
		storepayload.setStatus("active");

	}

	@Test(priority = 1)
	public void testpostorder() {
		Response res = StoreEndPointes.create_order(storepayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);

	}

	@Test(priority = 2)
	public void testgetorder() {

		int id = storepayload.getId();
		System.out.println("Order id is " + id);
		Response res = StoreEndPointes.get_order(this.storepayload.getId());
		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Test(priority = 3)
	public void testdeleteorder() {
		int id = storepayload.getId();
		Response res = StoreEndPointes.delete_order(this.storepayload.getId());
		Assert.assertEquals(res.getStatusCode(), 200);
	}

}
