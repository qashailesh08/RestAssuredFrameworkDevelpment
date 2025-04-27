package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import api.endpointes.UserEndPoints;
import api.payloads.UserModule;
import api.utilities.DataProvider;
import io.restassured.response.Response;

public class DDTests {

	Logger logger;

	@BeforeClass
	public void setlooger() {
		logger = LogManager.getLogger(this.getClass());
	}

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProvider.class)
	public void testpostuser(String userid, String username, String fname, String lname, String email, String pwd,
			String ph) {
		logger.info("================ Creating User =====================");
		UserModule userPayload = new UserModule();
		userPayload.setId(Integer.parseInt(userid));
		userPayload.setUsername(username);
		userPayload.setFirstname(fname);
		userPayload.setLastname(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		Response res = UserEndPoints.create_user(userPayload);
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("================ User Created =====================");

	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProvider.class)
	public void testdeleteuser(String username) {
		logger.info("================ Deleting User =====================");
		Response res = UserEndPoints.deleteUser(username);
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("================ User Deleted =====================");

	}
}
