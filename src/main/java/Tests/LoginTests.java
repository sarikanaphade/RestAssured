package Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import BaseClass.BaseTest;
import Constants.FileConstants;
import Utils.FileUtils;
import Utils.RestUtils;
import Utils.Utils;
import io.restassured.response.Response;

public class LoginTests extends BaseTest{
	
	
	@Test(dataProvider = "loginData", dataProviderClass = FileUtils.class)
	public void test_LoginScenarios(String username, String password, int respcode, String errorMsg) {
		BaseTest.threadLocalTest.get().info("Login with username : "+username+" and password : "+password);
		logger.info("Login with username : "+username+" and password : "+password);
		Response response = RestUtils.login(FileUtils.LoginPayload(username, password));
		String responseBody = response.getBody().asString();
		if(respcode == 201) {
			String token = response.jsonPath().getString("[0].token");
			System.out.println("Token : " + token);
			assertTrue(!token.isEmpty(),"Token is null or invalid");
			Utils.validateSchema(response, FileConstants.LOGIN_SCHEMA_PATH);
		}
		BaseTest.threadLocalTest.get().info("Response status code : "+response.statusCode());
		assertEquals(respcode,response.statusCode(),"Response code is incorrect : "+respcode);
		assertTrue(responseBody.contains(errorMsg),"Incorrect error message displayed: "+errorMsg);
		

	}
	
//	@Test
//	public void test_validLogin() {
//		Response response = RestUtils.login(FileUtils.LoginPayload());
//		assertEquals(response.statusCode(),201);
//		String userid = response.jsonPath().get("[0].userid");
//		System.out.println("valid login = " +userid);
//		String token = RestUtils.getToken(response);
//		System.out.println("valid login = " +token);
//		assertTrue(RestUtils.getToken(response)!=null,"Token is invalid");
//		assertTrue(userid!=null,"userid is not available");
//	   // "status": "username and password is incorrect"
//		//401 Unauthorized
//
//
//	}

}
