package api.endpointes;

public class Routes {
	// here we will store all "User Module " https requests
	// pet store swagger URL: https://petstore.swagger.io/
	// Create User (Post) URL: https://petstore.swagger.io/v2/user
	// Get User (Get) URL: https://petstore.swagger.io/v2/user/{username} //
	// user name will used to get the user details
	// Update User (Put) URL: https://petstore.swagger.io/v2/user/{username}
	// Delete User (Delete) URL: https://petstore.swagger.io/v2/user/{username}

	// User Modules URl's
	public static String userBase_url = "https://petstore.swagger.io/v2";
	public static String userPost_url = userBase_url + "/user";
	public static String userGet_url = userBase_url + "/user/{username}";
	public static String userUpdate_url = userBase_url + "/user/{username}";
	public static String userDelete_url = userBase_url + "/user/{username}";

	// In Routes class we used to store all modules url's like we can also store Pet
	// Modules URL's, Store Modules URL's and other also.

}
