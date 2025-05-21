package Utils;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestUtils {
	public static String token = null;
	
	public RestUtils() {
		RestAssured.baseURI = FileUtils.readData().getProperty("baseURI");
//		if(token == null) {
//			token = RestUtils.generateToken();
//		}
//		
	}
	
	public static Response login(String payload) {
		Response response = RestAssured.
							given().
							header("Content-Type", "application/json").
							body(payload).
							when()
							.post(FileUtils.readData().getProperty("login"));
		return response;
	}
	
	public static String getToken(Response response) {
		//Response response = login(FileUtils.LoginPayload());
        String token = response.jsonPath().get("[0].token");
        return token;
    }
	
	public static String getUserid(Response response) {
		//Response response = login(FileUtils.LoginPayload());
        String userid = response.jsonPath().get("[0].userid");
        return userid;
    }
	
	public static Response getData() {
		Response response = RestAssured.
							given().
							header("token",token).
							header("Content-Type","application/json").
							when().
							get(FileUtils.readData().getProperty("getData"));
		return response;
	}
	
	public static Response getData(String token) {
		Response response = RestAssured.
							given().
							header("token",token).
							header("Content-Type","application/json").
							when().
							get(FileUtils.readData().getProperty("getData"));
		return response;
	}
	
	
	public static Response addData(String payLoad, String endPoint) {
		Response response = RestAssured.
							given().
							header("Content-Type","application/json").
							header("token",token).
							body(payLoad).
							post(endPoint);
		return response;
	}
	
	public static Response addData(String payLoad, String endPoint,String token) {
		Response response = RestAssured.
							given().
							header("Content-Type","application/json").
							header("token",token).
							body(payLoad).
							post(endPoint);
		return response;
	}
	
	public static Response updateData(String payload, String endPoint) {
		Response response = RestAssured.
							given().
							header("Content-Type","application/json").
							header("token",token).
							body(payload).
							put(endPoint);
		return response;
	}

	public static Response updateData(String payload, String endPoint,String token) {
		Response response = RestAssured.
							given().
							header("Content-Type","application/json").
							header("token",token).
							body(payload).
							put(endPoint);
		return response;
	}
	
	public static Response deleteData(String payload, String endPoint,String token) {
		Response response = RestAssured.
							given().
							header("Content-Type","application/json").
							header("token",token).
							body(payload).
							delete(endPoint);
		return response;
	}
	
	public static Response deleteData(String payload, String endPoint) {
		Response response = RestAssured.
							given().
							header("Content-Type","application/json").
							header("token",token).
							body(payload).
							delete(endPoint);
		return response;
	}
	
	public static Response Logout(String endPoint) {
		Response response = RestAssured.
							given().
							header("Content-Type","application/json").
							header("token",token).
							post();
		return response;
		
	}
	

}
