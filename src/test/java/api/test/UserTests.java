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

		// DEBUG: Print Username and Status Code
		System.out.println("POST Username: " + userPayload.getUsername());
		System.out.println("POST Status Code: " + res.getStatusCode());
		System.out.println("POST Response: " + res.asString());

		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("************ User Created *************");
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		String username = userPayload.getUsername();
		Response res = retryGetUser(username, 3); // try 3 times

		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
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

	public Response retryGetUser(String username, int retries) {
		Response res = null;
		while (retries-- > 0) {
			res = UserEndPoints.getUser(username);
			if (res.getStatusCode() == 200)
				break;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
		}
		return res;
	}

}
