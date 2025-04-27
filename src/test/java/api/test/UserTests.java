package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpointes.UserEndPoints;
import api.payloads.UserModule;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	UserModule userPayload;
	public Logger logger;

	@BeforeClass
	public void setUserData() {
		faker = new Faker();
		userPayload = new UserModule();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 8));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setUserStatus(faker.number().hashCode());

		// Logs
		logger = LogManager.getLogger(this.getClass());

	}

	@Test(priority = 1)
	public void testpostuser() {
		logger.info("************ Creating User *************");
		Response res = UserEndPoints.create_user(userPayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("************ User Created *************");
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		logger.info("************ Reading User By User_name *************");
//		StaticWait.staticWait(3000);
		String name = userPayload.getUsername();
		System.out.println(name);
		Response res = UserEndPoints.readUser(this.userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("************ User info is displayed *************");
	}

	@Test(priority = 3)
	public void testUpdateUserByUserName() {
		logger.info("************ Updating User ************");
//		StaticWait.staticWait(3000);
		// update data by using pay-load
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		Response res = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);

		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);

		// checking data after update
//		StaticWait.staticWait(3000);
//		Response res1 = UserEndPoints.readUser(this.userPayload.getUsername());
//		res1.then().log().all();
//		Assert.assertEquals(res1.getStatusCode(), 200);
		logger.info("************ User Updated ************");

	}

	@Test(priority = 4)
	public void testDeleteUserByName() {
		logger.info("************ Deleting User ************");
//		StaticWait.staticWait(3000);
		Response res = UserEndPoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("*********** User Deleted ************");
	}
}
