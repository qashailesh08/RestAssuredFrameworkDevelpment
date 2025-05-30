package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import api.endpointes.StoreEndPointes;
import api.payloads.StoreModule;
import io.restassured.response.Response;

public class StoreTests {

	public StoreModule storepayload;
	public Logger logger;

	@BeforeClass
	public void createData() {
		storepayload = new StoreModule();
		storepayload.setId(1);
		storepayload.setPetId(199);
		storepayload.setCompelete(true);
		storepayload.setQuantity(1);
		storepayload.setShipedate("4-05-2025");
		storepayload.setStatus("active");
		logger = LogManager.getLogger(this.getClass());
		logger.info("Creating Loggers information..... | For Store API Test Cases..|");
	}

	@Test(priority = 1)
	public void testpostorder() {
		logger.info("============ Creating Order ==============");
		Response res = StoreEndPointes.create_order(storepayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("============ Order Created ==============");
	}

	@Test(priority = 2)
	public void testgetorder() {
		logger.info("============ Getting Order ==============");
		int id = storepayload.getId();
		System.out.println("Order id is " + id);
		Response res = StoreEndPointes.get_order(this.storepayload.getId());
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("============ Order Got ==============");
	}

	@Test(priority = 3)
	public void testdeleteorder() {
		logger.info("============ Deleting Order ==============");
		int id = storepayload.getId();
		Response res = StoreEndPointes.delete_order(id);
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("============ Order Deleted ==============");
	}

}
